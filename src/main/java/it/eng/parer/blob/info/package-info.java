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

/**
 * Questo package contiene i Data Trasfer Object utilizzati come payload delle code.
 * La struttura del payload {@link it.eng.parer.blob.info.PayLoad} è la seguente:
 * <ul>
 * <li>{@link it.eng.parer.blob.info.BlobInfo}
 * <ul>
 * <li>{@link it.eng.parer.blob.info.ChiaveDiRecupero}</li>
 * <li>vari attributi relativi al documento da migrare</li>
 * <li>{@link it.eng.parer.blob.info.Urn}</li>
 * <li>{@link it.eng.parer.blob.info.MigrazioneInfo} - struttura compilata per la coda <em>CODA_MIGRATI</em></li>
 * <li>{@link it.eng.parer.blob.info.VerificaInfo} - struttura compilata per la coda <em>CODA_VERIFICATI</em></li>
 * <li>{@link it.eng.parer.blob.info.ErroreInfo} - struttura compilata in caso di errore in <em>CODA_MIGRATI</em> oppure in <em>CODA_VERIFICATI</em></li> 
 * </ul>
 * </li>
 * </ul>
 *
 */
package it.eng.parer.blob.info;
