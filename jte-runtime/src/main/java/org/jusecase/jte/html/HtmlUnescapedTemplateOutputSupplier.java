package org.jusecase.jte.html;

public final class HtmlUnescapedTemplateOutputSupplier implements HtmlTemplateOutputSupplier {

    private final String value;

    public HtmlUnescapedTemplateOutputSupplier(String value) {
        this.value = value;
    }

    @Override
    public void writeContent(HtmlTemplateOutput output) {
        output.writeContent(value);
    }

    @Override
    public void writeTagBodyUserContent(HtmlTemplateOutput output, String tagName) {
        output.writeContent(value);
    }

    @Override
    public void writeTagAttributeUserContent(HtmlTemplateOutput output, String tagName, String attributeName) {
        output.writeContent(value);
    }
}