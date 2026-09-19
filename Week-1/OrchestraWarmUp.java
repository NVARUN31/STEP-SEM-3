abstract class Instrument {
    public abstract String play();
}

class StringInstrument extends Instrument {
    @Override
    public String play() {
        return "Strumming the strings";
    }
}

class Violin extends StringInstrument {
    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}

public class OrchestraWarmUp {
    public static void main(String[] args) {
        StringInstrument instrument = new StringInstrument();
        Violin violin = new Violin();

        System.out.println(instrument.play());
        System.out.println(violin.play());
    }
}
