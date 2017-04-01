package com.karmaworldwide.mail

import grails.plugin.asyncmail.AsynchronousMailService
import grails.transaction.Transactional

@Transactional
class SendMailService {
	def grailsApplication
    AsynchronousMailService asynchronousMailService
	public static final String ON = "on"
	
	def mail(fromEmail,email,fromSubject,htmlBody){
		String replyToEmail = grailsApplication.config.myapp.email.replyTo.toString()
        if(grailsApplication.config.email.send.toString() == ON){
		asynchronousMailService.sendMail {
			from fromEmail
			to email
//			replyTo replyToEmail
			subject fromSubject;
			html htmlBody;
		}
        }
	}
}