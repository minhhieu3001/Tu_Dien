package ALL.Model;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class TextSpeech {

    Synthesizer synthesizer;
    public TextSpeech() {
        try {
            System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

            Central.registerEngineCentral(
                    "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");

            synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
            //  synthesizer.getSynthesizerProperties().setPitch(1);
            synthesizer.allocate();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void speakWord(String word) {
        try {
            synthesizer.resume();
            synthesizer.speakPlainText(word, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            //synthesizer.deallocate();
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

}