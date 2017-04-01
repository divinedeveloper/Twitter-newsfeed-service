package com.karmaworldwide.user

import java.util.Set;

import com.karmaworldwide.account.User
import com.karmaworldwide.document.Documents;
//import grails.rest.*
/**
 * @author karmaworldwide
 * This Domain Class contains details of Mail format
 */
class MailFormat {

//    static marshalling = {
//        shouldOutputIdentifier true
//        shouldOutputVersion false
//        shouldOutputClass false
//        ignore 'user'
//    }

    String mailFor
	String body
	String mailSubject
	
	User user
	
	MailModule mailModule
	
	enum MailModule{
		ThankYouForRegistering,/*AddTeamMember,DonationMailToUser,DonatedMailToNGOCompany,
		PaymentFailedMailToUser,RegistrationMailToAdmin,ProjectAssignedMailToUser,
		UserDonatedRequestToNGO,ProjectNotificationMailToAdmin,ContactUs,*/Invitation,ProjectInvitationMailToCompany,
		/*VerificationEmail,*/RequestDocumentsFromNgo,RequestDocumentsFromCompany,DocumentsReceivedFromNgo,DocumentsReceivedFromCompany,/*RequestPaymentFromNgo,
		PaymentsReceivedThankYou,*/AccountActivatedNgo,AccountActivatedCompany/*,ThankYouForJoiningKarma,RegisteredCompany,RegisteredNgo,NewsLetter,
		UnsubscribeFromNewsLetter,ForgotPassword*/
	}
	
	static belongsTo = [user:User]
	
    static constraints = {
		mailModule nullable:true
		mailFor nullable:false,unique:true
		body nullable:true
		mailSubject nullable:false
		user nullable:true
    }
	
	static mapping = {
		body sqlType:"text"
	  }
	
	String getEnumValueWithSpace() {
		switch(mailFor)
		{
			case "ThankYouForRegistering":
			return "Thank You For Registering"
			/*case "AddTeamMember":
			return "Add Team Member"
			case "DonationMailToUser":
			return "Donation Mail To User"
			case "DonatedMailToNGOCompany":
			return "Donated Mail To NGO Company"
			case "PaymentFailedMailToUser":
			return "Payment Failed Mail To User"
			case "RegistrationMailToAdmin":
			return "Registration Mail To Admin"
			case "ProjectAssignedMailToUser":
			return "Project Assigned Mail To User"
			case "UserDonatedRequestToNGO":
			return "User Donated Request To NGO"*/
			/*case "ProjectNotificationMailToAdmin":
			return "Project Notification Mail To Admin"
			case "ContactUs":
			return "Contact Us"*/
			case "Invitation":
			return "Invitation"
			case "ProjectInvitationMailToCompany":
			return "Project Invitation Mail To Company"
			/*case "VerificationEmail":
			return "Verification Email"*/
			case "RequestDocumentsFromNgo":
			return "Request Documents From Ngo"
			case "RequestDocumentsFromCompany":
			return "Request Documents From Company"
			case "DocumentsReceivedFromNgo":
			return "Documents Received From Ngo"
			case "DocumentsReceivedFromCompany":
			return "Documents Received From Company"
			/*case "RequestPaymentFromNgo":
			return "Request Payment From Ngo"
			case "PaymentsReceivedThankYou":
			return "Payments Received Thank You"*/
			case "AccountActivatedNgo":
			return "Account Approved for Ngo"
			case "AccountActivatedCompany":
			return "Account Approved for Company"
			/*case "ThankYouForJoiningKarma":
			return "Thank You For Joining Karma"
			case "RegisteredCompany":
			return "Registered Company"
			case "RegisteredNgo":
			return "Registered Ngo"
			case "NewsLetter":
			return "NewsLetter"
			case "UnsubscribeFromNewsLetter":
			return "Unsubscribe From News Letter"
			case "ForgotPassword":
			return "Forgot Password"*/
		}
	}
}
