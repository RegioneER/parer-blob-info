/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna
 * <p/>
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package it.eng.parer.blob.info;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * URN del documento.
 *
 * Il documento/componente versato viene identificato, all'interno della sua struttura di appartenenza, tramite un URN.
 * Nel corso degli anni le specifiche relative al formato sono cambiate. Per questo motivo qui ne indichiamo tre
 * versioni differenti.
 *
 * @author Snidero_L
 */
public class Urn implements Serializable {

    private static final long serialVersionUID = 1849988575809917186L;

    private String iniziale;

    @NotNull(message = "L'urn originale non può essere vuoto (BLOB_INFO.URN.URN_ORIGINALE)")
    private String originale;

    @NotNull(message = "L'urn normalizzato non può essere vuoto (BLOB_INFO.URN.URN_NORMALIZZATO)")
    private String normalizzato;

    public Urn() {
        // Corpo lasciato intenzionamente vuoto
    }

    public String getIniziale() {
        return iniziale;
    }

    public void setIniziale(String iniziale) {
        this.iniziale = iniziale;
    }

    public String getOriginale() {
        return originale;
    }

    public void setOriginale(String originale) {
        this.originale = originale;
    }

    public String getNormalizzato() {
        return normalizzato;
    }

    public void setNormalizzato(String normalizzato) {
        this.normalizzato = normalizzato;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
