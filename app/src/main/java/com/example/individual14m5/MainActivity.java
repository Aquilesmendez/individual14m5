package com.example.individual14m5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.individual14m5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    //Se crea los atributos a usar.
    private ActivityMainBinding Binding;
    private boolean isFragmentShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Configuramos el atributo viewBinding y enlazamos el diseño con el codigo.
        Binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        //Creamos el evento escuchador para abrir o cerrar el fragmento dependendiendo el estado.
        Binding.botonAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verificamos si es verdadero o falso la variable dependiendo usamos una u otra funcion.
                if (isFragmentShow){
                    cerrarFragmento();
                }else {
                    abrirFragmento();
                }
            }
        });
    }

    //Creamos el metodo para poder llamar al fragmento.
    private void abrirFragmento (){
        //Instanciamos el fragmento.
        QuestionFragment questionFragment = new QuestionFragment();
        //Instanciamos el fragmentManager para poder acceder a distintos metodos.
        FragmentManager manager = getSupportFragmentManager();
        //Se llama al fragmento y se agrega a la vista especificada mediante el metodo add.
        FragmentTransaction transaction = manager.beginTransaction().add(
                Binding.contenedor.getId(),questionFragment,QuestionFragment.class.getSimpleName());
        //Ejecuta la transaccion.
        transaction.commit();
        //Seteamos el valor de la variable a true cuando abre el fragmento.
        isFragmentShow = true;
        //Seteamos el valor del texto del boton.
        Binding.botonAbrir.setText("CERRAR");
    }

    //Creamos la funcion para poder cerrar el fragmento.
    private void cerrarFragmento (){

        //Instanciamos el fragmentManager para poder acceder a distintos metodos.
        FragmentManager manager = getSupportFragmentManager();
        //Instanciamos el fragmento.
        QuestionFragment questionFragment = (QuestionFragment) manager.findFragmentById(Binding.contenedor.getId());
        //Verificamos que el fragmento no este cerrado o no sea nulo.
        if (questionFragment != null){
            //Creamos un objeto del tipo FragmenTransaccion para poder gestionar el fragmento.
            FragmentTransaction transaction = manager.beginTransaction();
            // cerramos y ejecutamos con el commit.
            transaction.remove(questionFragment).commit();
            //Seteamos el valor del boton.
            Binding.botonAbrir.setText("ABRIR");
            //Seteamos el valor de la variable a false cuando cierra el fragmento.
            isFragmentShow = false;
        }
    }
}