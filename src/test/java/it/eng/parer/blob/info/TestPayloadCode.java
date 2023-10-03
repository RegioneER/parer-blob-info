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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test sulla composizione dei Payload.
 *
 * Questi test sono stati utilizzati soprattutto per generare i payload di esempio per la documentazione.
 *
 * @author Snidero_L
 */
public class TestPayloadCode {

    private final Logger log = LoggerFactory.getLogger(TestPayloadCode.class);

    private static ObjectMapper jsonMapper;
    private static Validator validator;

    private ChiaveDiRecupero chiaveDiRecupero;
    private Urn urn;
    private ErroreInfo erroreInserimento;
    private ErroreInfo erroreVerifica;
    private MigrazioneInfo migrazioneInfo;
    private VerificaInfo verificaInfo;
    private BlobInfo baseBlobInfo;
    private PayLoad basePayLoad;

    public TestPayloadCode() {
    }

    @BeforeClass
    public static void setUpClass() {
        jsonMapper = new ObjectMapper();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        chiaveDiRecupero = new ChiaveDiRecupero();
        chiaveDiRecupero.setIdBlob(42L);
        chiaveDiRecupero.setNomeColonnaId("ID_CONTEN_DOC");
        chiaveDiRecupero.setNomeTabella("ARO_CONTENUTO_COMP");
        chiaveDiRecupero.setNomeColonnaBlob("BL_CONTEN_COMP");

        urn = new Urn();
        urn.setOriginale("urn:AMBIENTE PROVA 1:ENTE PROVA 1:STRUTTURA PROVA 1:DELIBERE-2011-1/12-PRINCIPALE:01:01");
        urn.setIniziale("urn:AMBIENTE PROVA 1:ENTE PROVA 1:STRUTTURA PROVA 1:DELIBERE-2011-1.12-PRINCIPALE:01:01");
        urn.setNormalizzato("urn:AMBIENTE_PROVA_1:ENTE_PROVA_1:STRUTTURA_PROVA_1:DELIBERE-2011-1.12-PRINCIPALE:01:01");

        erroreInserimento = new ErroreInfo();
        erroreInserimento.setTipologia(ErroreInfo.TipologiaErrore.INSERIMENTO);
        erroreInserimento.setCodice("E01");
        erroreInserimento.setMessaggio("Impossibile contattare l'object storage");
        erroreInserimento.setHashCalcolato(null);
        erroreInserimento.setTimeStamp(randomTimeStamp());

        erroreVerifica = new ErroreInfo();
        erroreVerifica.setTipologia(ErroreInfo.TipologiaErrore.VERIFICA);
        erroreVerifica.setCodice("EXA1");
        erroreVerifica.setMessaggio("Hash calcolata e hash ricevuta non combaciano");
        erroreVerifica.setHashCalcolato("f042bf21dc1d11d077c9bb7a1c2056c9851351c8");
        erroreVerifica.setTimeStamp(randomTimeStamp());

        verificaInfo = new VerificaInfo();
        verificaInfo.setTimeStamp(randomTimeStamp());
        verificaInfo.setHashEncoding("hexBinary");
        verificaInfo.setHash("e55d4445b1377e901509c68f6214db471951da33e5143a804322a92f9ec756e9");

        migrazioneInfo = new MigrazioneInfo();
        migrazioneInfo.setTimeStamp(randomTimeStamp());
        migrazioneInfo.seteTag("37f58230658c14c16b0db9af5de95996");

        baseBlobInfo = new BlobInfo();
        baseBlobInfo.setChiave(chiaveDiRecupero);
        baseBlobInfo.setTenant("SACER_TEST");
        baseBlobInfo.setBucket("componenti-test");
        baseBlobInfo.setKey("SACER_TEST/provincia_di_modena/AOO_AO_MODENA/PROTOCOLLO_INFORMATICO/2011/123454789");
        baseBlobInfo.setDimensione(889080123L);
        baseBlobInfo.setTipoHash("SHA-1");
        baseBlobInfo.setHash("5fb3a83c89849a2e89f9716a04f92c25d94dce89");
        baseBlobInfo.setHashEncoding("hexBinary");
        baseBlobInfo.setTimeStamp(randomTimeStamp());
        baseBlobInfo.setTipoHashDaCalcolare("SHA-256");
        baseBlobInfo.setDataVersamento(randomTimeStamp());
        baseBlobInfo.setUrn(urn);

        basePayLoad = new PayLoad();
        basePayLoad.setBlobInfo(baseBlobInfo);

    }

    private static long randomTimeStamp() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        long nextLong = threadLocalRandom.nextLong(89438324L);
        return nextLong + System.currentTimeMillis();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testChiave() throws IOException {
        String ser = chiaveDiRecupero.toString();
        ChiaveDiRecupero readValue = jsonMapper.readValue(ser, ChiaveDiRecupero.class);
        print("Esempio di chiave di recupero", chiaveDiRecupero);

        assertThat(chiaveDiRecupero, is(readValue));
    }

    @Test
    public void testErrore() throws IOException {

        ErroreInfo erroreInsParsed = jsonMapper.readValue(erroreInserimento.toString(), ErroreInfo.class);
        ErroreInfo erroreVerificaParsed = jsonMapper.readValue(erroreVerifica.toString(), ErroreInfo.class);
        print("Esempio di errore durante l'inserimento:", erroreInserimento);
        print("Esempio di errore durante la verifica:", erroreVerifica);

        assertThat(erroreInsParsed, is(erroreInserimento));

        assertThat(erroreVerificaParsed, is(erroreVerifica));
    }

    @Test
    public void testBlobDaMigrare() throws IOException {
        final PayLoad payload = basePayLoad;
        print("1/7 H3 - Esempio di blob da migrare:");
        print("Questo è il payload di input per il processo MIGRATORE.", payload);

        PayLoad payloadParsed = jsonMapper.readValue(payload.toString(), PayLoad.class);
        assertThat(payloadParsed, is(payload));
    }

    @Test
    public void testBlobMigrato() throws IOException {
        final PayLoad payload = basePayLoad;
        payload.setMigrazioneInfo(migrazioneInfo);

        print("2/7 H3 - Esempio di blob migrato:");
        print("Questo è il payload di input per il processo VERIFICATORE.", payload);

        PayLoad payloadParsed = jsonMapper.readValue(payload.toString(), PayLoad.class);
        assertThat(payloadParsed, is(payload));
    }

    @Test
    public void testBlobVerificato() throws IOException {
        final PayLoad payload = basePayLoad;
        payload.setMigrazioneInfo(migrazioneInfo);
        payload.setVerificaInfo(verificaInfo);
        print("4/7 H3 - Esempio di blob verificato:");
        print("Questo è il payload di input per il processo di SACER in caso inserimento avvenuto con successo.\nIl payload è stato recuperato da SACER dalla coda CODA_VERIFICATI.",
                payload);

        PayLoad payloadParsed = jsonMapper.readValue(payload.toString(), PayLoad.class);
        assertThat(payloadParsed, is(payload));
    }

    @Test
    public void testBlobVerificatoSha256() throws IOException {
        final PayLoad payload = basePayLoad;
        payload.getBlobInfo().setTipoHash("SHA-256");
        payload.getBlobInfo().setHash("e55d4445b1377e901509c68f6214db471951da33e5143a804322a92f9ec756e9");
        payload.setMigrazioneInfo(migrazioneInfo);
        payload.setVerificaInfo(verificaInfo);

        print("5/7 H3 - Esempio di blob verificato (SHA-256):");
        print("L'esempio è analogo al precedente con la differenza che l'hash del file prodotto da SACER è di tipo SHA-256.",
                payload);

        PayLoad payloadParsed = jsonMapper.readValue(payload.toString(), PayLoad.class);
        assertThat(payloadParsed, is(payload));
    }

    @Test
    public void testBlobErroreInserimento() throws IOException {
        final PayLoad payload = basePayLoad;
        payload.setErroreInfo(erroreInserimento);

        print("3/7  H3 - Esempio di errore durante l'inserimento:");
        print("Questo è il payload di input per il processo di SACER in caso di errore di inserimento.\nIl payload viene aggiunto alla CODA_ERRATI dal processo MIGRATORE.",
                payload);

        PayLoad payloadParsed = jsonMapper.readValue(payload.toString(), PayLoad.class);
        assertThat(payloadParsed, is(payload));
    }

    @Test
    public void testBlobErroreVerifica() throws IOException {
        final PayLoad payload = basePayLoad;
        payload.setMigrazioneInfo(migrazioneInfo);
        payload.setErroreInfo(erroreVerifica);

        print("7/7 H3 - Esempio di errore durante la verifica:");
        print("Questo è il payload di input per il processo di SACER in caso errore durante la verifica.\nIl payload è stato recuperato da SACER dalla coda CODA_ERRATI.",
                payload);

        PayLoad payloadParsed = jsonMapper.readValue(payload.toString(), PayLoad.class);
        assertThat(payloadParsed, is(payload));

    }

    @Test
    public void testBlobVerificatoInputSha1OutputSha512() throws IOException {
        final PayLoad payload = basePayLoad;
        basePayLoad.getBlobInfo().setTipoHashDaCalcolare("SHA-512");
        payload.setMigrazioneInfo(migrazioneInfo);
        verificaInfo.setHash(
                "41a9f3210136f44164751e85f0419775a53a53dd1f2530a332809dc643f48eae23b4714843f44566c1c8c028d80a369e0a51c2dcdf66ffd713a801a9d3621026");
        payload.setVerificaInfo(verificaInfo);

        print("6/7 Esempio di blob verificato (Input SHA-1 output SHA-512):");
        print("Caso analogo al precedente con richiesta di calcolo hash SHA-512 da parte del servizio VERIFICATORE.",
                payload);

        PayLoad payloadParsed = jsonMapper.readValue(payload.toString(), PayLoad.class);
        assertThat(payloadParsed, is(payload));
    }

    @Test
    public void testEmptyBlobInfoValidation() {

        log.info("\nTest empty BlobInfo");
        PayLoad payLoad = basePayLoad;
        basePayLoad.setBlobInfo(new BlobInfo());

        Set<ConstraintViolation<PayLoad>> validate = validator.validate(payLoad);
        for (ConstraintViolation<PayLoad> constraintViolation : validate) {
            System.err.println(
                    "\t" + constraintViolation.getMessage() + " valore errato: " + constraintViolation.getInvalidValue()
                            + " per la proprietà " + constraintViolation.getPropertyPath());
        }
        assertThat(validate.size(), is(11));
    }

    @Test
    public void testEmptyKeyValidation() {

        log.info("\nTest empty BlobInfo Key");
        PayLoad payLoad = basePayLoad;
        basePayLoad.getBlobInfo().setChiave(new ChiaveDiRecupero());

        Set<ConstraintViolation<PayLoad>> validate = validator.validate(payLoad);
        for (ConstraintViolation<PayLoad> constraintViolation : validate) {
            System.err.println(
                    "\t" + constraintViolation.getMessage() + " valore errato: " + constraintViolation.getInvalidValue()
                            + " per la proprietà " + constraintViolation.getPropertyPath());
        }
        assertThat(validate.size(), is(4));
    }

    @Test
    public void testEmptyUrnValidation() {

        log.info("\nTest empty BlobInfo URN");

        PayLoad payLoad = basePayLoad;
        basePayLoad.getBlobInfo().setUrn(new Urn());

        Set<ConstraintViolation<PayLoad>> validate = validator.validate(payLoad);
        for (ConstraintViolation<PayLoad> constraintViolation : validate) {
            System.err.println(
                    "\t" + constraintViolation.getMessage() + " valore errato: " + constraintViolation.getInvalidValue()
                            + " per la proprietà " + constraintViolation.getPropertyPath());
        }
        assertThat(validate.size(), is(2)); // was 3
    }

    @Test
    public void testPayloadValidation() {

        log.info("\nTest empty BlobInfo Key from payload");

        basePayLoad.getBlobInfo().setChiave(null);

        Set<ConstraintViolation<PayLoad>> validate = validator.validate(basePayLoad);
        for (ConstraintViolation<PayLoad> constraintViolation : validate) {
            log.error(
                    "\t" + constraintViolation.getMessage() + " valore errato: " + constraintViolation.getInvalidValue()
                            + " per la proprietà " + constraintViolation.getPropertyPath());
        }
        assertThat(validate.size(), is(1));
    }

    @Test
    public void testUrnInizialeVuoto() {
        log.info("\nTest empty URN iniziale");

        basePayLoad.getBlobInfo().getUrn().setIniziale(null);

        Set<ConstraintViolation<PayLoad>> validate = validator.validate(basePayLoad);
        assertThat(validate.size(), is(0));
    }

    private void print(String message) throws JsonProcessingException {
        print(message, null);
    }

    private void print(String message, Object payload) throws JsonProcessingException {
        log.info(message);

        if (payload != null) {
            String writeValueAsString = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);
            log.info(writeValueAsString);
        }

    }
}
