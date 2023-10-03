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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Componente principale del Payload delle code.
 *
 * Tale parte del payload è compilata da Sacer e fa parte del payload inserito
 * nelle code:
 * <ul>
 * <li>CODA_DA_MIGRARE</li>
 * <li>CODA_MIGRATI</li>
 * <li>CODA_VERIFICATI</li>
 * <li>CODA_ERRATI</li>
 * </ul>
 *
 *
 * @author Snidero_L
 */
public class BlobInfo implements Serializable {

    private static final long serialVersionUID = -8475852183838785527L;

    @NotNull(message = "La chiave di recupero non può essere vuota (BLOB_INFO.CHIAVE)")
    @Valid
    private ChiaveDiRecupero chiave;

    @NotNull(message = "Tipo hash non può essere vuoto (BLOB_INFO.TIPO_HASH)")
    private String tipoHash;

    @NotNull(message = "Hash non può essere vuoto (BLOB_INFO.HASH)")
    @Size(min = 40, message = "L'hash fornito deve essere lunga almeno 40 caratteri (lunghezza di un hash SHA1) (BLOB_INFO.HASH)")
    private String hash;

    @NotNull(message = "Hash encoding non può essere vuoto (BLOB_INFO.HASH_ENCODING)")
    private String hashEncoding; //99% dei casi sarà hexBinary 

    @NotNull(message = "Il tenant deve essere valorizzato (BLOB_INFO.TENANT)")
    private String tenant;

    @NotNull(message = "Il bucket deve essere valorizzato (BLOB_INFO.BUCKET)")
    private String bucket;

    @NotNull(message = "La chiave deve essere valorizzata (BLOB_INFO.KEY)")
    private String key;

    @Min(value = 1, message = "La dimensione del file da migrare deve essere valorizzato correttamente (BLOB_INFO.DIMENSIONE)")
    private long dimensione;

    @Min(value = 1, message = "Il timestamp di produzione del payload deve essere valorizzato correttamente (BLOB_INFO.TIMESTAMP)")
    private long timeStamp;

    // Diventato opzionale
    private String tipoHashDaCalcolare;

    @Min(value = 1, message = "Data versamento deve essere valorizzata correttamente (BLOB_INFO.DATA_VERSAMENTO)")
    private long dataVersamento;

    @NotNull(message = "L'urn non può essere vuoto (BLOB_INFO.URN)")
    @Valid
    private Urn urn;

    public BlobInfo() {
        // Corpo lasciato intenzionamente vuoto
    }

    public ChiaveDiRecupero getChiave() {
        return chiave;
    }

    public void setChiave(ChiaveDiRecupero chiave) {
        this.chiave = chiave;
    }

    public String getTipoHash() {
        return tipoHash;
    }

    public void setTipoHash(String tipoHash) {
        this.tipoHash = tipoHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHashEncoding() {
        return hashEncoding;
    }

    public void setHashEncoding(String hashEncoding) {
        this.hashEncoding = hashEncoding;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getDimensione() {
        return dimensione;
    }

    public void setDimensione(long dimensione) {
        this.dimensione = dimensione;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTipoHashDaCalcolare() {
        return tipoHashDaCalcolare;
    }

    public void setTipoHashDaCalcolare(String tipoHashDaCalcolare) {
        this.tipoHashDaCalcolare = tipoHashDaCalcolare;
    }

    public long getDataVersamento() {
        return dataVersamento;
    }

    public void setDataVersamento(long dataVersamento) {
        this.dataVersamento = dataVersamento;
    }

    public Urn getUrn() {
        return urn;
    }

    public void setUrn(Urn urn) {
        this.urn = urn;
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
