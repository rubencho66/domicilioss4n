package co.com.domilicilios.builder;

import java.util.List;

public interface InstructionBuilder {

    List<String> readInstruction();

    void writePosition(List<String> informationList);

}
