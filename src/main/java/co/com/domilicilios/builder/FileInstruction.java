package co.com.domilicilios.builder;

import co.com.domilicilios.dao.PropertiesDao;
import co.com.domilicilios.dao.impl.PropertiesDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileInstruction implements InstructionBuilder {

    final static Logger LOGGER = LoggerFactory.getLogger(FileInstruction.class);

    private static final String PATH_INITIAL = "FilesInput/input";
    private static final String PATH_OUTPUT = "FilesOutput/output";
    private static final String EXTENSION = ".txt";

    private PropertiesDao dao;

    public FileInstruction() {
        this.dao = new PropertiesDaoImpl();
    }

    @Override
    public List<String> readInstruction() {
        List<String> directionList = new ArrayList<>();
        File myFile = new File(PATH_INITIAL + Thread.currentThread().getName() + EXTENSION);
        try {
            Scanner myReader = new Scanner(myFile);
            final int size = dao.getSize();
            while (myReader.hasNextLine()) {
                if (!(directionList.size() <= size)) break;
                directionList.add(myReader.nextLine().replaceAll("\\s","").toUpperCase());
            }
            if (Objects.nonNull(myReader)) {
                myReader.close();
            }
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return directionList;
    }

    @Override
    public void writePosition(List<String> informationList) {
        try {
            FileWriter file = new FileWriter(PATH_OUTPUT + Thread.currentThread().getName() + EXTENSION);
            PrintWriter pw = new PrintWriter(file);
            for (String information : informationList) {
                pw.println(information);
            }
            if (Objects.nonNull(file)) {
                file.close();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
