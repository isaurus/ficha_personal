package com.example.fichapersonal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaración de EditText para introducir el nombre de usuario
    EditText edtUserName;

    // Declaración de TextView para mostrar las cadenas de caracteres
    TextView txtUserName, txtCourse, txtLanguages;

    // Declaración de int para almacenar el índice seleccionado en el RadioGroup de AlertDialog para Course
    int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vínculación de TextView a XML
        txtUserName = findViewById(R.id.txtUserName);
        txtCourse = findViewById(R.id.txtCourse);
        txtLanguages = findViewById(R.id.txtLanguages);

        // Vinculación de Layout personalizado a XML
        //final View customLayout = getLayoutInflater().inflate(R.layout.user_name_layout,null);
        //edtUserName = customLayout.findViewById(R.id.edtUserName);

        edtUserName = new EditText(this);

        findViewById(R.id.btnName).setOnClickListener(this::createNameDialog);
        findViewById(R.id.btnCourse).setOnClickListener(this::createCourseDialog);
        findViewById(R.id.btnLanguages).setOnClickListener(this::createLanguageDialog);
        findViewById(R.id.btnNewInscription).setOnClickListener(this::newInscription);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("Name", txtUserName.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        txtUserName.setText(savedInstanceState.getString("Name", ""));
    }


    private void newInscription(View v){

    }


    private void clearSelection(boolean[] selectedItems){
        for (int i = 0; i < selectedItems.length; i++){
            selectedItems[i] = false;
        }
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

    /**
     * Método para mostrar en el TextView el RadioButton seleccionado.
     *
     * @param itemList La lista de items del AlertDialog
     * @param id El id del botón seleccionado
     */
    protected void showCourse(String[] itemList, int id){
        txtCourse.setText(itemList[id]);
    }

    /**
     * Método para mostrar en el TextView los CheckBox seleccionados.
     *
     * @param chkList El String[] que almacena el texto de cada CheckBox
     * @param selectedItems El boolean[] que almacena si el CheckBox está seleccionado
     */
    protected void showLanguages(String[] chkList, boolean[] selectedItems){
        String result = "";
        for (int i = 0; i < chkList.length; i++){
            if (selectedItems[i]){
                result += chkList[i] + "\n";
            }
        }
        txtLanguages.setText(result);
    }

    /**
     * Método para lanzar un AlertDialog con un EditText. Recoge la cadena introducida y la muestra
     * en el TextView
     *
     * @param v La View (Button) que se pulsa
     */
    protected void createNameDialog(View v){
        EditText edtUserName = new EditText(MainActivity.this);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getText(R.string.title_name).toString())
                .setView(edtUserName)
                .setNegativeButton(getText(R.string.btn_cancel), null)
                .setPositiveButton(getText(R.string.btn_accept), (dialog, which) -> showUserName(edtUserName))
                .create()
                .show();
    }

    /**
     * Método para lanzar un AlertDialog con RadioButton. Al seleccionar uno y pulsar PositiveButton
     * se muestra el texto del RadioButton seleccionado en el TextView.
     *
     * @param v La View (Button) que se pulsa
     */
    protected void createCourseDialog(View v){
        final String[] itemList = new String[]{
                getString(R.string.rbt_dam),
                getString(R.string.rbt_daw),
                getString(R.string.rbt_asir)
        };

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getText(R.string.title_course))
                .setSingleChoiceItems(itemList, -1, (dialog, which) ->{
                    selectedItem = which;
                })
                .setPositiveButton(getString(R.string.btn_accept), (dialog, which) -> showCourse(itemList, selectedItem))
                .setNegativeButton(getString(R.string.btn_cancel), null)
                .create()
                .show();
    }

    /**
     * Método para lanzar un AlertDialog con varios CheckBox. Al seleccionarlos y pulsar el
     * PositiveButton se muestra el TextView
     *
     * @param v La View (Button) que se pulsa
     */
    protected void createLanguageDialog(View v){
        final String[] chkList = new String[]{
                getString(R.string.chk_java),
                getString(R.string.chk_javascript),
                getString(R.string.chk_csharp),
                getString(R.string.chk_kotlin),
                getString(R.string.chk_phyton)
        };

        boolean[] selectedItems = new boolean[chkList.length];

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getText(R.string.title_languages))
                .setMultiChoiceItems(chkList, selectedItems, (dialog, which, isChecked) -> selectedItems[which] = isChecked)
                .setPositiveButton(getString(R.string.btn_accept), (dialog, which) -> showLanguages(chkList, selectedItems))
                .setNegativeButton(getString(R.string.btn_cancel), null)
                .setNeutralButton(getString(R.string.btnClearAll), (dialog, which) -> clearSelection(selectedItems))
                .create()
                .show();
    }
}