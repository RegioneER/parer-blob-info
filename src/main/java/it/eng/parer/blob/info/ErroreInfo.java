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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Componente del payload compilato per la coda <em>CODA_ERRATI</em>.
 *
 * @author Snidero_L
 */
public class ErroreInfo implements Serializable {

    private static final long serialVersionUID = 3307484827434113182L;

    public enum TipologiaErrore {
        INSERIMENTO, VERIFICA
    }

    private TipologiaErrore tipologia;
    private String codice;
    private String messaggio;
    private String hashCalcolato;
    private long timeStamp;

    public ErroreInfo() {
        // Corpo lasciato intenzionamente vuoto
    }

    public ErroreInfo(TipologiaErrore tipologia) {
        this.tipologia = tipologia;
    }

    public TipologiaErrore getTipologia() {
        return tipologia;
    }

    public void setTipologia(TipologiaErrore tipologia) {
        this.tipologia = tipologia;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getHashCalcolato() {
        return hashCalcolato;
    }

    public void setHashCalcolato(String hashCalcolato) {
        this.hashCalcolato = hashCalcolato;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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
