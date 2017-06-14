package com.oo.tpp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class poetry extends AppCompatActivity {
    public static int stPoezije = 4;
    public static TextView vsebina;
    public static String [] poezije = {"Dragotin Kette: Na molu San Carlo\n" +
            "(odlomek)\n" +
            "\n" +
            "Toda zaprl sem ji ustnice zlobne,\n" +
            "s strastnim poljubom zaprl sem jih ...\n" +
            "Čolnič hiti ko labud belogrudi,\n" +
            "vetrc objema nas lahen in tih.\n" +
            "\n" +
            "(Kette 1949: 34)\n" +
            "\n" +
            "\n" +
            "\n" +
            "Janko Samec: Molo San Carlo\n" +
            "(odlomek)\n" +
            "\n" +
            "Tu je tedaj nekoč naš slavni Kette\n" +
            "se razgovarjal s šumom morskih vod?\n" +
            "Tu izklesal najlepše je sonete\n" +
            "in druge pesmi za slovenski rod\n" +
            "[...]\n" +
            "A tukaj drugi sanjajo sanjači\n" +
            "in so bolj mrki kakor bil je On.\n" +
            "\n" +
            "(Samec 1981: 23)\n" +
            "\n" +
            "\n" +
            "\n" +
            "Umberto Saba: Il molo\n" +
            "(odlomek) \n" +
            "\n" +
            "Per me al mondo non v’ha un più caro e fido\n" +
            "luogo di questo. Dove mai più solo\n" +
            "mi sento e in buona compagnia che al molo\n" +
            "San Carlo.\n" +
            "\n" +
            "(Saba 2001: 117)\n" +
            "\n" +
            "\tPrevod\n" +
            "\t\n" +
            "Ni zame na svetu bolj dragega in domačega kraja od tega. \n" +
            "Le kje se počutim tako samega \n" +
            "in v dobri družbi kot na Pomolu \n" +
            "svetega Karla.\n" +
            "\n" +
            "\n" +
            "Carlo Mioni: Molo Audace\n" +
            "(odlomek)\n" +
            "\n" +
            "De San Carlo se ciamava\n" +
            "Questo molo. Nel disdoto\n" +
            "Qua l’ »Audace« con Petiti\n" +
            "Ancorà se ga de soto.\n" +
            "\n" +
            "Questo molo per Trieste\n" +
            "Xe un gloruioso monumento. \n" +
            "\n" +
            "(Mioni 1941: 37)\n" +
            "\n" +
            "\tPrevod\n" +
            "\t\n" +
            "Po svetem Karlu je dobil ime \n" +
            "ta pomol. Leta osemnajst \n" +
            "je Petiti tu spodaj \n" +
            "zasidral ‚Audace‘.\n" +
            "\n" +
            "Ta pomol je za Trst\n" +
            "slaven spomenik",

            "Augusto levi:\n" +
            "\n" +
            "Xe secoli passai, me cara e bela\n" +
            "Dileta ciesa ti xe sempre là,\n" +
            "Amor, speranza, luminosa stela\n" +
            "Che indora coi sui ragi la cità.\n" +
            "\n" +
            "I archi, i simulacri a noi ne svela\n" +
            "Chi forte, immortal vita ti ge dà,\n" +
            "Nè tempo, nè nemizi no cancela\n" +
            "El nasser tuo, la tua italianità.\n" +
            "\n" +
            "(Levi 1915: 21)\n" +
            "\n" +
            "\tPrevod\n" +
            "Minila so stoletja, a ti, moja draga in lepa, \n" +
            "preljuba cerkev, si še vedno tam, \n" +
            "ljubezen, upanje, svetla zvezda, \n" +
            "ki mesto pozlati s svojimi žarki. \n" +
            "\n" +
            "Oboki, kipi nam razodevajo,  \n" +
            "kdo vdahnil življenje ti je večno, \n" +
            "niti čas niti sovražniki ne bodo \n" +
            "izbrisali tvojih italijanskih korenin.\n" +
            "\n" +
            "\n" +
            "\n" +
            "Riccardi Pitteri:\n" +
            "\n" +
            "Ma dal vetusto tempio, austero e cupo\n" +
            "Quale un gigante di macigno, parla\n" +
            "Lieto e sereno nella sua fortezza\n" +
            "San Giusto, biondo giovinetto e: amore\n" +
            "Dice, amor vi congiunga, o cittadini,\n" +
            "Amor di patria come me possente\n" +
            "Di gaglïarda giovinezza e, come\n" +
            "Questa mia secolar rocca, perenne. \n" +
            "\n" +
            "(Pitteri 1906: 259–260) .\n" +
            "\n" +
            "\tPrevod\n" +
            "Iz starodavnega templja, strog in mrk \n" +
            "kot kamniti velikan, spregovori \n" +
            "radosten in veder v svoji trdnjavi\n" +
            "sveti Just, svetlolasi mladenič in: ljubezen, \n" +
            "reče, ljubezen naj vas združi, o, meščani, \n" +
            "ljubezen do domovine, kakor jaz polna \n" +
            "mladostne zagnanosti in, kakor \n" +
            "ta moja stoletna trdnjava, večna.",

            "Mariano Rugo: Punto franco\n" +
            "(odlomek)\n" +
            "\n" +
            "Le mondatrici di caffé, sedute\n" +
            "sul ballatoio di cemento, al vento\n" +
            "triste del mare lavorano. Spento\n" +
            "giunge un odor di terre sconosciute. \n" +
            "\n" +
            "(Rugo 1939: 47)\n" +
            "\n" +
            "\tPrevod\n" +
            "Čistilke kave, sedeč \n" +
            "na cementnem mostovžu, v žalostnem \n" +
            "morskem vetru, delajo. Zamolkel \n" +
            "prihaja vonj neznanih dežel.«\n" +
            "\n" +
            "\n" +
            "Stanko Kosovel: Brez dela\n" +
            "(odlomek)\n" +
            "\n" +
            "Četrti mesec ... Dan na dan\n" +
            "na cesti in na trgu čaka\n" +
            "in išče dela.\n" +
            "Dan na dan odhaja in prihaja z móla\n" +
            "mračán, brez radostnih novic.\n" +
            "[...]\n" +
            "Petleten ôtrok mu leži\n" +
            "na smrt bolan od silne bede,\n" +
            "a dela od nikoder ni ...\n" +
            "[...]\n" +
            "Izložba se pred njim zasveti.\n" +
            "»Draguljarna«.\n" +
            "[...]\n" +
            "Na sinka misli, na družino,\n" +
            "razsodnost pojenjava bolj in bolj.\n" +
            "\n" +
            "(Kosovel 1981: 76)\n" +
            "\n" +
            "\n" +
            "Srečko Kosovel: Čujem z obali\n" +
            "(odlomek)\n" +
            "\n" +
            "Čujem z obali žvižganje parnikov\n" +
            "čujem prikrito ječanje dvigalnikov,\n" +
            "čujem strojev težko brnenje,\n" +
            "čujem pod njimi vzdih v življenje!\n" +
            "\n" +
            "(Kosovel 1974: 214)\n" +
            "\n", "Mariano Rugo: Punto franco\n" +
            "(odlomek)\n" +
            "\n" +
            "Le mondatrici di caffé, sedute\n" +
            "sul ballatoio di cemento, al vento\n" +
            "triste del mare lavorano. Spento\n" +
            "giunge un odor di terre sconosciute. \n" +
            "\n" +
            "(Rugo 1939: 47)\n" +
            "\n" +
            "Prevod\n" +
            "Čistilke kave, sedeč \n" +
            "na cementnem mostovžu, v žalostnem \n" +
            "morskem vetru, delajo. Zamolkel \n" +
            "prihaja vonj neznanih dežel.«\n" +
            "\n" +
            "\n" +
            "\n" +
            "Stanko Kosovel: Brez dela\n" +
            "(odlomek)\n" +
            "\n" +
            "Četrti mesec ... Dan na dan\n" +
            "na cesti in na trgu čaka\n" +
            "in išče dela.\n" +
            "Dan na dan odhaja in prihaja z móla\n" +
            "mračán, brez radostnih novic.\n" +
            "[...]\n" +
            "Petleten ôtrok mu leži\n" +
            "na smrt bolan od silne bede,\n" +
            "a dela od nikoder ni ...\n" +
            "[...]\n" +
            "Izložba se pred njim zasveti.\n" +
            "»Draguljarna«.\n" +
            "[...]\n" +
            "Na sinka misli, na družino,\n" +
            "razsodnost pojenjava bolj in bolj.\n" +
            "\n" +
            "(Kosovel 1981: 76)\n" +
            "\n" +
            "\n" +
            "\n" +
            "Srečko Kosovel: Čujem z obali\n" +
            "(odlomek)\n" +
            "\n" +
            "Čujem z obali žvižganje parnikov\n" +
            "čujem prikrito ječanje dvigalnikov,\n" +
            "čujem strojev težko brnenje,\n" +
            "čujem pod njimi vzdih v življenje!\n" +
            "\n" +
            "(Kosovel 1974: 214)\n",

            "Za lokacijo poezije niso na voljo."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poetry);

        vsebina = (TextView) findViewById(R.id.vsebina);
        Bundle b = getIntent().getExtras();
        if(b != null)
            stPoezije = b.getInt("stPoezije");

        vsebina.setText(poezije[stPoezije]);
    }
}
