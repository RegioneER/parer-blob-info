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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Struttura generale del payload delle code. Contiene le informazioni di:
 * <ul>
 * <li>{@link BlobInfo}: informazioni prodotte da SACER</li>
 * <li>{@link MigrazioneInfo}: informazioni prodotte dal micro-servizio di migrazione</li>
 * <li>{@link VerificaInfo}: informazioni prodotte dal micro-servizio di verifica</li>
 * <li>{@link ErroreInfo}: informazioni prodotte dai micro-servizii di migrazione o di verifica in caso di errore</li>
 * </ul>
 *
 * @author Snidero_L
 */
public class PayLoad implements Serializable {

    private static final long serialVersionUID = 4693159529632010880L;
    @Valid
    @NotNull(message = "BlobInfo non può essere vuoto (BLOB_INFO)")
    private BlobInfo blobInfo;
    private MigrazioneInfo migrazioneInfo;
    private VerificaInfo verificaInfo;
    private ErroreInfo erroreInfo;

    public PayLoad() {
        // Corpo lasciato intenzionamente vuoto
    }

    public BlobInfo getBlobInfo() {
        return blobInfo;
    }

    public void setBlobInfo(BlobInfo blobInfo) {
        this.blobInfo = blobInfo;
    }

    public MigrazioneInfo getMigrazioneInfo() {
        return migrazioneInfo;
    }

    public void setMigrazioneInfo(MigrazioneInfo migrazioneInfo) {
        this.migrazioneInfo = migrazioneInfo;
    }

    public VerificaInfo getVerificaInfo() {
        return verificaInfo;
    }

    public void setVerificaInfo(VerificaInfo verificaInfo) {
        this.verificaInfo = verificaInfo;
    }

    public ErroreInfo getErroreInfo() {
        return erroreInfo;
    }

    public void setErroreInfo(ErroreInfo erroreInfo) {
        this.erroreInfo = erroreInfo;
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
