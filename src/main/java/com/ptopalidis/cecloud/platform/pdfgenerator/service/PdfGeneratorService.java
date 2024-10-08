package com.ptopalidis.cecloud.platform.pdfgenerator.service;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.lowagie.text.pdf.BaseFont;
import com.ptopalidis.cecloud.platform.common.exception.GlobalException;
import com.ptopalidis.cecloud.platform.common.exception.error.TemplateNotFoundError;
import com.ptopalidis.cecloud.platform.pdfgenerator.domain.FileTemplate;
import com.ptopalidis.cecloud.platform.pdfgenerator.domain.FileTemplateAsset;
import com.ptopalidis.cecloud.platform.pdfgenerator.domain.FileTemplateType;
import com.ptopalidis.cecloud.platform.pdfgenerator.repository.FileTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PdfGeneratorService {

    private final FileTemplateRepository fileTemplateRepository;

    public ByteArrayOutputStream generatePdfFromTemplate(String templateName, Map<String, Object> params) throws IOException {

        FileTemplate template = this.fileTemplateRepository.findByName(templateName).orElseThrow(()->new GlobalException(new TemplateNotFoundError()));
        URL url = new URL(template.getUrl());
        try (InputStream in = url.openStream();
             ByteArrayOutputStream buffer = new ByteArrayOutputStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
             ) {
            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            Handlebars handlebars = new Handlebars();
            Template handlebarsTemplate = handlebars.compileInline(buffer.toString());

            String html = handlebarsTemplate.apply(params);

            Document document = Jsoup.parse(html, "UTF-8");
            document.outputSettings()
                    .syntax(Document.OutputSettings.Syntax.xml);

            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);

            List<FileTemplateAsset> fontAssets = template.getAssets().stream().filter(fileTemplateAsset -> fileTemplateAsset.getType().equals(FileTemplateType.FONT)).toList();
            for(FileTemplateAsset fontAsset: fontAssets){
                renderer.getFontResolver().addFont(fontAsset.getUrl(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            }
            renderer.setDocumentFromString(document.html());
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream;
        }

    }
}
