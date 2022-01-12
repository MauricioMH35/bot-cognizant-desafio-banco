package org.dio.cognizant.banco.configs;

import org.dio.cognizant.banco.controllers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class RepositoryFactory {
    private static Properties properties = new Properties();
    private static String pathname = "config.properties";

    // Controllers
    private static DepositarController depositarCtrl;
    private static SacarController sacarCtrl;
    private static TransferirController transferirCtrl;
    private static ExtratoController extratoCtrl;
    private static AbrirContaController abrirContaCtrl;

    static {
        FileReader reader = null;

        try {
            File file = new File(pathname);
            reader = new FileReader(file);
            properties.load(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getInstance(String className) {
        Class aClass = null;

        try {
            aClass = Class.forName(className);
            Object instance = aClass.getDeclaredConstructor().newInstance();
            return instance;
        } catch (ClassNotFoundException |
                InvocationTargetException |
                InstantiationException |
                IllegalAccessException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static DepositarController getDepositarCtrl() {
        if (depositarCtrl == null) {
            String classname = properties.getProperty("DepositarController");
            depositarCtrl = (DepositarController) getInstance(classname);
        }

        return depositarCtrl;
    }

    public static SacarController getSacarCtrl() {
        if (sacarCtrl == null) {
            String classname = properties.getProperty("SacarController");
            sacarCtrl = (SacarController) getInstance(classname);
        }
        return sacarCtrl;
    }

    public static TransferirController getTransferirCtrl() {
        if (transferirCtrl == null) {
            String classname = properties.getProperty("TransferirController");
            transferirCtrl = (TransferirController) getInstance(classname);
        }
        return transferirCtrl;
    }

    public static ExtratoController getExtratoCtrl() {
        if (extratoCtrl == null) {
            String classname = properties.getProperty("ExtratoController");
            extratoCtrl = (ExtratoController) getInstance(classname);
        }
        return extratoCtrl;
    }

    public static AbrirContaController getAbrirContaCtrl() {
        if (abrirContaCtrl == null) {
            String classname = properties.getProperty("AbrirContaController");
            abrirContaCtrl = (AbrirContaController) getInstance(classname);
        }
        return abrirContaCtrl;
    }
}
