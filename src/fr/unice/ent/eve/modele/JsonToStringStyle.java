package fr.unice.ent.eve.modele;

import org.apache.commons.lang3.builder.ToStringStyle;

/**
* <p><code>ToStringStyle</code> that outputs with JSON format.</p>
*
* <p>This is an inner class rather than using
* <code>StandardToStringStyle</code> to ensure its immutability.</p>
*/
public final class JsonToStringStyle extends ToStringStyle {
    
    private static final long serialVersionUID = 1L;
    
    /**
* <p>Constructor.</p>
*
* <p>Use the static constant rather than instantiating.</p>
*/
    JsonToStringStyle() {
        super();
        
        this.setUseClassName(false);
        this.setUseIdentityHashCode(false);

        this.setContentStart("{");
        this.setContentEnd("}");

        this.setArrayStart("[");
        this.setArrayEnd("]");

        this.setFieldSeparator(",");
        this.setFieldNameValueSeparator(":");
        

        this.setNullText("null");
        
        this.setSummaryObjectStartText("\"<");
        this.setSummaryObjectEndText(">\"");
        
        this.setSizeStartText("\"<size=");
        this.setSizeEndText(">\"");
    }
    
    @Override
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {

        if (value == null) {
            
            appendNullText(buffer, fieldName);
            
        } else if (value instanceof String) {
            
            appendValueAsString(buffer, (String)value);
            
        } else {
            
            buffer.append(value);
        }
    }

    private void appendValueAsString(StringBuffer buffer, String value) {
        
        buffer.append("\"" + value + "\"");
    }

    /**
* <p>Ensure <code>Singleton</code> after serialization.</p>
*
* @return the singleton
*/
    private Object readResolve() {
        return this;
    }
    
}

