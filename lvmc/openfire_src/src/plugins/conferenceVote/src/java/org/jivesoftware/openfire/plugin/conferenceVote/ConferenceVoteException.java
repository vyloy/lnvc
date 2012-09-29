package org.jivesoftware.openfire.plugin.conferenceVote;

public class ConferenceVoteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ConferenceVoteException(String message){
		super(message);
	}
	
	public ConferenceVoteException(){
		super();
	}
}
