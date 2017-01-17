package com.bugbusters.lajarus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuestActivity extends AppCompatActivity {


    private Runnable task = new Runnable() {
        public void run() {
            Intent intent = new Intent(QuestActivity.this,MapActivity.class);
            startActivity(intent);
        }
    };

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    Toast.makeText(getApplicationContext(),
                            "Nooooooo!",
                            Toast.LENGTH_SHORT).show();

                Handler handler = new Handler();
                int millisDelay = 3000;
                handler.postDelayed(task, millisDelay);
                    break;
            case R.id.radioButton2:
                if (checked)
                    Toast.makeText(getApplicationContext(),
                            "Success!",
                            Toast.LENGTH_SHORT).show();
                    Handler handler1 = new Handler();
                    int millisDelay1 = 3000;
                    handler1.postDelayed(task, millisDelay1);
                    break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        TextView editText =(TextView)findViewById(R.id.editText2);
        editText.setText(" O Ηρακλής διατάχθηκε από τον Ευρυσθέα να οδηγήσει στις Μυκήνες από την Ερύθεια, ένα απομονωμένο νησί στον Ωκεανό, τα βόδια του βασιλιά Γηρυόνη, γιου του Χρυσάορα και της Καλλιρρόης. Μετά από μακρά και περιπετειώδη πορεία στις χώρες της Μεσογείου, και αφού μονομάχησε με τον Γηρυόνη στις ακτές του ποταμού Ανθεμούντα, έφτασε στις ιωνικές ακτές της Ελλάδας, όπου μια βοϊδόμυγα, σταλμένη από την Ήρα, τρέλανε το κοπάδι και το έκανε να διασκορπιστεί στα βουνά της Θράκης.\n" +
                "Ο Ηρακλής κατηγόρησε τον Στρυμόνα, ότι δυσκόλεψε το έργο του να συγκεντρώσει τα ζώα. Τον γέμισε λοιπόν με πέτρες, και από τότε ο ποταμός έπαψε να είναι πλωτός (Απολλόδ. 2.112).\n" +
                "Σύμφωνα με άλλη εκδοχή του μύθου, ο Ηρακλής δεν μπορούσε να διασχίσει τον ποταμό επειδή δεν υπήρχε πέρασμα. Έριξε λοιπόν τεράστιους βράχους εμποδίζοντας τα πλοία να τον διαπλέουν.\n" +
                "Κατά μία άλλη εκδοχή οι κάτοικοι του κάμπου των Σερρών, παραπονέθηκαν στον ήρωα Ηρακλή όταν επέστρεφε από τη Θράκη, για τις μεγάλες ζημιές που τους προκαλούσε ο ποταμός Στρυμόνας με τις συχνές πλημμύρες του. Ο Ηρακλής τότε διευθέτησε την κοίτη και απάλλαξε τους κατοίκους από τις φοβερές καταστροφές. Οι κάτοικοι της περιοχής, που διασχίζει ο ποταμός Στρυμόνας τον θεωρούσαν Θεό. Στο Μουσείο της Αμφίπολης υπάρχει ανάλογο αρχαίο νόμισμα.\n" +
                "\n" +
                "ΣΕ ΠΟΙΟΝ ΑΘΛΟ ΤΟΥ ΗΡΑΚΛΗ ΕΓΙΝΑΝ ΤΑ ΠΑΡΑΠΑΝΩ;");
    }
}
