package com.narvaezjdario

import grails.plugin.springsecurity.annotation.Secured

class ProfileController {

	def springSecurityService
	
	@Secured(value=["hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')"])
    def index() { 
		def userInstance = springSecurityService.currentUser
		model:[userInstance:userInstance]
	}
	
	@Secured(value=["hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')"])
	def editUser(){
		def roleInstance = Role.findByAuthority('ROLE_USER')
		def userInstance = User.get(params.idUser)
		userInstance.properties = params
		if (!userInstance.save(flush:true)) {
			render template:'../errorInstance', model:[errorInstance:userInstance], status:400
			return
		}
		
		
		UserRole.create(userInstance, roleInstance, true)
		render(contentType: 'text/json') {[
			'message': 'Actualización Exitosa',
		    'fullName': userInstance.fullName,
			'email': userInstance.email,
		    'status': 200
		]}
	}
	
	@Secured(value=["hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')"])
	def chagePhoto(){
		def userInstance = springSecurityService.currentUser
		def photo = userInstance.photo
		
		
		def f = request.getFile('file')
		def nameInputFile = f.originalFilename
		def typeFile = nameInputFile.substring(nameInputFile.lastIndexOf('.'), nameInputFile.length())
		def webrootDir = servletContext.getRealPath("/") //app directory
		def nameFile = UUID.randomUUID().toString()+typeFile
		def dirFile = "images/profiles/"+nameFile
		File fileDest = new File(webrootDir, dirFile)
		if(!fileDest.exists()){
			if(!fileDest.mkdirs()){
				render 'Error al crear el archivo'
				return
			}
		}
		
		try{
			f.transferTo(fileDest)
		}catch(Error e){
			render 'Error al almacenar la información'
			return
		}
		
		def pictureInstance
		
		if(photo){
			File fileDest2 = new File(webrootDir, photo.urlPicture)
			if(fileDest2.exists()){
				fileDest2.delete()
			}
			pictureInstance = photo
			pictureInstance.urlPicture = dirFile
			pictureInstance.name = nameFile
			
		}else{
			pictureInstance = new Picture(name: nameFile, urlPicture: dirFile)
		}
		
		pictureInstance.save(flush:true)
		userInstance.photo = pictureInstance
		userInstance.save(flush:true)
		
		render 'listo'
	}
}
