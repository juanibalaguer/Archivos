package com.example.archivos;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    Context context;
    MutableLiveData<String> error;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void crearEstructura() {
        File carpeta = new File(context.getFilesDir(), "/Almacen");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        File archivo = new File(carpeta, "/archivo.dat");
        try {
            archivo.createNewFile();
        } catch (IOException e) {
            error.setValue(e.getMessage());
        }

        guardarDatos(archivo);
        leerDatos(archivo);
    }

    public void guardarDatos(File archivo){

        try {
            String marca = "BMW";
            int modelo = 100;
            double precio = 200.20;
            FileOutputStream fo = new FileOutputStream(archivo);
            BufferedOutputStream bos = new BufferedOutputStream(fo);
            /*DataOutputStream dos = new DataOutputStream(bos);
            dos.writeUTF(marca);
            dos.writeInt(modelo);
            dos.writeDouble(precio);*/
            Auto auto = new Auto("BMW", 123, 5000.12);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(auto);
            bos.flush();
            fo.close();



        } catch (FileNotFoundException e) {
            error.setValue(e.getMessage());
        } catch (IOException e) {
            error.setValue(e.getMessage());
        }


    }

    public void leerDatos(File archivo){
        try {
            StringBuffer nombre = new StringBuffer();
            FileInputStream fis  = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            /*DataInputStream dis = new DataInputStream(bis);
            String marca = dis.readUTF();
            int modelo = dis.readInt();
            double precio = dis.readDouble();*/
            ObjectInputStream ois = new ObjectInputStream(bis);
            Auto auto = (Auto) ois.readObject();
            Log.d("Salida 1: ", "Marca: " + auto.getMarca());
            Log.d("Salida 2: ", "Modelo: " + auto.getModelo() + "");
            Log.d("Salida 3: ", "Precio: " + auto.getPrecio() + "");

            int caracter=0;
            while((caracter = bis.read())!=-1) {
                if ( caracter != "#".charAt(0)) {
                    nombre.append((char) caracter);
                } else {
                    nombre.append("\n");
                }
            }
            fis.close();

        } catch (FileNotFoundException e) {
            error.setValue(e.getMessage());
        } catch (IOException e) {
            error.setValue(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }



}
