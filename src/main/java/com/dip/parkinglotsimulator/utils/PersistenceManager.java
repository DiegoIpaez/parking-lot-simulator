package com.dip.parkinglotsimulator.utils;

import java.io.*;

public class PersistenceManager {

    /**
     * Serializa y guarda cualquier objeto en un archivo binario (.dat).
     *
     * @param object   El objeto a persistir. Debe implementar Serializable.
     * @param filePath Ruta del archivo donde se guardará (ej: "parking_lot.dat").
     */
    public static <T> void save(T object, String filePath) {
        try {
            // Abre (o crea) el archivo en la ruta indicada
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            // Lo "envuelve" para poder escribir objetos Java, no solo bytes crudos
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Convierte el objeto a bytes y lo escribe en el archivo
            objectOutputStream.writeObject(object);
            objectOutputStream.close();

            System.out.println("💾 Datos guardados correctamente en: " + filePath);

        } catch (IOException ioException) {
            System.out.println("❌ Error al guardar los datos: " + ioException.getMessage());
        }
    }

    /**
     * Carga y deserializa un objeto desde un archivo binario (.dat).
     * Si el archivo no existe o hay un error, retorna el valor por defecto.
     *
     * @param filePath     Ruta del archivo a leer (ej: "parking_lot.dat").
     * @param defaultValue Valor que se retorna si el archivo no existe o falla la
     *                     lectura.
     * @return El objeto deserializado, o defaultValue si no se pudo cargar.
     */
    public static <T> T load(String filePath, T defaultValue) {
        File dataFile = new File(filePath);

        boolean fileDoesNotExist = !dataFile.exists();
        if (fileDoesNotExist) {
            System.out.println("📄 No se encontró archivo de datos. Iniciando con estado vacío.");
            return defaultValue;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(dataFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // readObject() retorna Object, el cast (T) le indica a Java que confíe en que el contenido del archivo es de tipo T
            T loadedObject = (T) objectInputStream.readObject();
            objectInputStream.close();

            System.out.println("📂 Datos cargados correctamente desde: " + filePath);
            return loadedObject;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ No se pudo cargar, usando valor por defecto.");
            return defaultValue;
        }
    }
}