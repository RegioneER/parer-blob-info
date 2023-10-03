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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Parte del componente {@link BlobInfo}.
 * <p>
 * Tramite questo oggetto è possibile identificare univocamente sul database di SACER il documento da migrare.
 * </p>
 *
 * @author Snidero_L
 */
public class ChiaveDiRecupero implements Serializable {

    private static final long serialVersionUID = 1537321751974875880L;

    /**
     * RegExp per identificare i caratteri ammessi per identificare le tabelle/colonne su ORACLE. Accetta solamente nomi
     * composti da un minimo di 1 ad un massimo di 30 caratteri e composti da
     * <ul>
     * <li>lettere maiuscole;</li>
     * <li>carattere "_";</li>
     * <li>carattere "-";</li>
     * <li>carattere ".".</li>
     * </ul>
     * La regexp è la seguente:
     * 
     * <pre>
     * ^([A-Z]|[_\\-\\.]){1,30}$
     * </pre>
     */
    private static final String CARATTERI_PERMESSI = "^([A-Z]|[a-z]|[_\\-\\.]){1,30}$";

    @Min(value = 1, message = "L'id del blob deve essere valorizzato (BLOB_INFO.CHIAVE.ID_BLOB)")
    private long idBlob;

    @NotNull(message = "Il nome della colonna del blob deve essere valorizzato (BLOB_INFO.CHIAVE.NOME_COLONNA_BLOB)")
    @Pattern(regexp = CARATTERI_PERMESSI, message = "Il formato del nome della colonna del blob è errato (BLOB_INFO.CHIAVE.NOME_COLONNA_BLOB)")
    private String nomeColonnaBlob;

    @NotNull(message = "Il nome della colonna dell'id deve essere valorizzato (BLOB_INFO.CHIAVE.NOME_COLONNA_ID)")
    @Pattern(regexp = CARATTERI_PERMESSI, message = "Il formato del nome della colonna dell'id è errato (BLOB_INFO.CHIAVE.NOME_COLONNA_ID)")
    private String nomeColonnaId;

    @NotNull(message = "Il nome della tabella deve essere valorizzato (BLOB_INFO.CHIAVE.NOME_TABELLA)")
    @Pattern(regexp = CARATTERI_PERMESSI, message = "Il formato del nome della tabella è errato (BLOB_INFO.CHIAVE.NOME_TABELLA)")
    private String nomeTabella;

    public ChiaveDiRecupero() {
        // Corpo lasciato intenzionamente vuoto
    }

    public long getIdBlob() {
        return idBlob;
    }

    public void setIdBlob(long idBlob) {
        this.idBlob = idBlob;
    }

    public String getNomeColonnaBlob() {
        return nomeColonnaBlob;
    }

    public void setNomeColonnaBlob(String nomeColonnaBlob) {
        this.nomeColonnaBlob = nomeColonnaBlob;
    }

    public String getNomeColonnaId() {
        return nomeColonnaId;
    }

    public void setNomeColonnaId(String nomeColonnaId) {
        this.nomeColonnaId = nomeColonnaId;
    }

    public String getNomeTabella() {
        return nomeTabella;
    }

    public void setNomeTabella(String nomeTabella) {
        this.nomeTabella = nomeTabella;
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
