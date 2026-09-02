package project;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsService {

    private static final String DRL_PATH = "com/project/rules/ProinvestBG.drl";
    private static final KieContainer KIE_CONTAINER = buildKieContainer();

    private static KieContainer buildKieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieFileSystem kfs = ks.newKieFileSystem();
        kfs.write("src/main/resources/" + DRL_PATH,
                ks.getResources().newClassPathResource(DRL_PATH, DroolsService.class));

        KieBuilder kieBuilder = ks.newKieBuilder(kfs);
        kieBuilder.buildAll();

        if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
            throw new IllegalStateException("Greška pri kompajliranju Drools pravila: "
                    + kieBuilder.getResults().getMessages(Message.Level.ERROR));
        }

        return ks.newKieContainer(kieBuilder.getKieModule().getReleaseId());
    }

    public PreporukaGradnje pokreniPravila(ParcelaInput unos) {

        KieSession kSession = KIE_CONTAINER.newKieSession();
        PreporukaGradnje preporuka = new PreporukaGradnje();

        try {
            kSession.insert(unos);
            kSession.insert(preporuka);
            kSession.fireAllRules();
        } finally {
            kSession.dispose();
        }

        return preporuka;
    }
}
