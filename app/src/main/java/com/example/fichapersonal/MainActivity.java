package com.example.fichapersonal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaración de EditText para introducir el nombre de usuario
    EditText edtUserName;

    // Declaración de TextView para mostrar las cadenas de caracteres
    TextView txtUserName, txtCourse, txtLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vínculación de TextView a XML
        txtUserName = findViewById(R.id.txtUserName);

        // Vinculación de Layout personalizado a XML
        //final View customLayout = getLayoutInflater().inflate(R.layout.user_name_layout,null);
        //edtUserName = customLayout.findViewById(R.id.edtUserName);

        edtUserName = new EditText(this);

        findViewById(R.id.btnName).setOnClickListener(this::createNewDialogTest);
        findViewById(R.id.btnCourse).setOnClickListener(this::setCourse);
        findViewById(R.id.btnLanguages).setOnClickListener(this::setLanguage);
        findViewById(R.id.btnNewInscription).setOnClickListener(this::resetAll);
    }

    private void setCourse(View v) {
    }

    private void setLanguage(View v) {
    }

    private void resetAll(View v){

    }

    /**
     * Método para mostrar en el TextView la cadena de caracteres
     * introducida en el EditText del AlertDialog.
     *
     * @param edtUserName El EditText del AlertDialog
     */
    private void showUserName(EditText edtUserName){
        String userName = edtUserName.getText().toString();
        txtUserName.setText(userName);
    }

    protected void showCourse(String[] itemList){
        txtCourse.setText();
    }

    /**
     * Método para crear el AlertDialog al pulsar el botón "Nombre". Instancia un EditText
     * que se pasa como parámetro a .setView(). El PositiveButton hace una llamada a
     * showUserName() con el EditText como parámetro para obtener la cadena de caracteres introducida.
     *
     * @param v La View (Button) que recibe al pulsar el Button de "Nombre"
     */
    /*protected void createNameDialog(View v){
        EditText edtUserName = new EditText(MainActivity.this);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getText(R.string.title_name).toString())
                .setView(edtUserName)
                .setNegativeButton(getText(R.string.btn_cancel), null)
                .setPositiveButton(getText(R.string.btn_accept).toString(), (dialog, which) -> showUserName(edtUserName))
                .create()
                .show();
    }*/

    /**
     * Método de prueba que lanza el AlertDialog correspondiente en tiempo de ejecución al pulsar
     * uno de los Button.
     *
     * @param v La View (Button) que recibe al pulsar el Button
     */
    protected void createNewDialogTest(View v){

        int viewId = v.getId();

        if(viewId == R.id.btnName){
            EditText edtUserName = new EditText(MainActivity.this);

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getText(R.string.title_name).toString())
                    .setView(edtUserName)
                    .setNegativeButton(getText(R.string.btn_cancel), null)
                    .setPositiveButton(getText(R.string.btn_accept).toString(), (dialog, which) -> showUserName(edtUserName))
                    .create()
                    .show();

        } else if (viewId == R.id.btnCourse) {
            final String[] itemList = new String[]{getString(R.string.rbt_dam), getString(R.string.rbt_daw), getString(R.string.rbt_asir)};
            int checkedItem[] = new int[]{};

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle(getText(R.string.title_course).toString())
                    .setSingleChoiceItems(itemList, checkedItem[-1], (dialog, which) -> showCourse(itemList))
                    .create()
                    .show();

        } else if (viewId == R.id.btnLanguages) {

        }
    }
}