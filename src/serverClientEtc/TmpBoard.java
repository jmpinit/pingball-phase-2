package serverClientEtc;

import java.io.*;

public class TmpBoard {
	
	private String name;
	
	TmpBoard (String name) {
		this.name = name;
	}
	
	TmpBoard (File file) {
		FileReader in;
        try {
            in = new FileReader(file);
            BufferedReader br = new BufferedReader(in);
            name = br.readLine();
            br.close();
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	public String name() {
		return name;
	}

}
