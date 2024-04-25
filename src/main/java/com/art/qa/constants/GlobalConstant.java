package com.art.qa.constants;

public class GlobalConstant {	
	

	public enum ValidationMessage {
		BlankUserNameValidation("* This field is required."), BlankPasswordValidation("* This field is required."),
		WrongUserNameValidation("Username or Password is incorrect. Please re-enter."),
		WrongPasswordValidation("Username or Password is incorrect. Please re-enter.");

		private String value;

		private ValidationMessage(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
		}

	}

	public enum dataImportValidation {
		NotSelectedDataImportTypeValidation("Data Import Type is mandatory."),
		BlankProfileNameValidation("Profile Name is mandatory."),
		BlankSourceFileLocationValidation("Source File Location is mandatory."),
		WrongImportFileExtensionValidation("Invalid import file extension");

		private String value;

		private dataImportValidation(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
		}

	}

}