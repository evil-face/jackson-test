package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

@JsonDeserialize(keyUsing = CustomKey.CustomKeyDeserializer.class)
@JsonSerialize(keyUsing = CustomKey.CustomKeySerializer.class)
public class CustomKey {
    private String keyName;
    private String keyCategory;

    @JsonCreator
    public CustomKey(@JsonProperty(value = "keyName") String keyName,
                     @JsonProperty(value = "keyCategory") String keyCategory) {
        this.keyName = keyName;
        this.keyCategory = keyCategory;
    }

    public CustomKey() {
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyCategory() {
        return keyCategory;
    }

    public void setKeyCategory(String keyCategory) {
        this.keyCategory = keyCategory;
    }

    public static class CustomKeyDeserializer extends KeyDeserializer {
        @Override
        public CustomKey deserializeKey(String key, DeserializationContext ctx) throws IOException {
            String[] parts = key.split("-");
            return new CustomKey(parts[0], parts[1]);
        }
    }

    public static class CustomKeySerializer extends JsonSerializer<CustomKey> {
        @Override
        public void serialize(CustomKey value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeFieldName(value.getKeyName() + "-" + value.getKeyCategory());
        }
    }
}
