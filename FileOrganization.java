package students;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOrganization {

	static String location = "C:\\Users\\sunan\\Desktop\\java programs\\studentDetails";
	static File directory = new File(location);

	public static boolean insertStudent(String studentId, String data) throws IOException {

		String fileName = studentId + ".txt";
		File[] files = directory.listFiles();
		for (File file : files) {
			System.out.println(file);
			if (file.getName().equals(fileName))
				return false;
		}
		System.out.print(data);
		String absolutePath = location + "\\" + fileName;

		File studentFile = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			studentFile = new File(absolutePath);
			studentFile.createNewFile();
			fileWriter = new FileWriter(studentFile);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(data);
		} catch (Exception IOException) {
			return false;
		} finally {
			if (bufferedWriter != null)
				bufferedWriter.close();
			if (fileWriter != null)
				fileWriter.close();

		}
		return true;

	}

	public static boolean validateStudent(String id, String pass) throws IOException, RegistrationFormException {
		boolean loginFileFound = false;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		File[] files = directory.listFiles();
		String absolutePath = location + "\\" + id.toUpperCase() + ".txt";
		File loginFile = new File(absolutePath);

		for (File file : files) {
			if (file.equals(loginFile))
				loginFileFound = true;
		}
		if (loginFileFound) {
			try {
				fileReader = new FileReader(loginFile);
				bufferedReader = new BufferedReader(fileReader);
				String string = null;
				String passwordString = null;
				while ((string = bufferedReader.readLine()) != null) {
					passwordString = string;
				}
				String password = passwordString.split(":")[1].trim();
				if (password.equals(pass))
					return true;
				else
					throw new RegistrationFormException("wrong password");
			} catch (IOException e) {
			} finally {
				if (fileReader != null)
					fileReader.close();
				if (bufferedReader != null)
					bufferedReader.close();
			}
		} else {
			throw new RegistrationFormException("invalid Id");
		}
		return false;

	}

	public static List<String> getSearchedData(String name) throws IOException {
		List<String> list = new ArrayList<>();

		File[] files = directory.listFiles();
		for (File file : files) {

			String fileName = file.getName();
			if (fileName.length() == 14) {
				if (Validation.isValidId(fileName)) {
					FileReader fileReader = null;
					BufferedReader bufferedReader = null;
					try {
						fileReader = new FileReader(file);
						bufferedReader = new BufferedReader(fileReader);
						String nameLine = bufferedReader.readLine();
					
						String data = nameLine;
						System.out.println(name);
						if (nameLine.toLowerCase().contains(name.toLowerCase())) {
							System.out.println("yes !");
							for (int i = 0; i < 2; i++) {
								data = data + "\n" + bufferedReader.readLine();
							}
							list.add(data);
							data = null;
						}
					}
					catch(Exception e) {}
					finally {
						if (fileReader != null)
							fileReader.close();
						if (bufferedReader != null)
							bufferedReader.close();
						
					}
				}
			}
		}

		return list;
	}

	public static String getStudentDetails(String id) throws IOException {
		String absolutePath = location + "\\" + id + ".txt";
		File file = new File(absolutePath);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		StringBuffer data = new StringBuffer();
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				data.append(str+"\n");
			}

		} catch (Exception e) {
		} finally {
			if (fileReader != null)
				fileReader.close();
			if (bufferedReader != null)
				bufferedReader.close();
		}
		return data.toString();

	}
}
