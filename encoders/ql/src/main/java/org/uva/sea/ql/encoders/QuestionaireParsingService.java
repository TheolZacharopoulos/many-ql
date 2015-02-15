package org.uva.sea.ql.encoders;

/**
 * Service for parsing questionaire input files.
 * 
 * @author Pim Tegelaar
 */
public interface QuestionaireParsingService {

	/**
	 * Parses the input file and creates a questionaire.
	 * 
	 * @param location
	 *            the location of the input file.
	 * @return The {@link Questionaire} that was parsed from the input file.
	 */
	Questionaire parse(String location);

}
