package com.imc.siemens_aas.i4_0.message.frame;

import lombok.Data;

import java.util.Objects;

@Data
public class Key {
    private String type;
    private String idType;
    private String value;
    private Boolean local;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return Objects.equals(type, key.type) && Objects.equals(idType, key.idType) && Objects.equals(value, key.value) && Objects.equals(local, key.local);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, idType, value, local);
    }
}
