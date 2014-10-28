package com



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProduitWSController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Produit.list(params), [status: OK]
    }

    @Transactional
    def save(Produit produitInstance) {
        if (produitInstance == null) {
            render status: NOT_FOUND
            return
        }

        produitInstance.validate()
        if (produitInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        produitInstance.save flush:true
        respond produitInstance, [status: CREATED]
    }

    @Transactional
    def update(Produit produitInstance) {
        if (produitInstance == null) {
            render status: NOT_FOUND
            return
        }

        produitInstance.validate()
        if (produitInstance.hasErrors()) {
            render status: NOT_ACCEPTABLE
            return
        }

        produitInstance.save flush:true
        respond produitInstance, [status: OK]
    }

    @Transactional
    def delete(Produit produitInstance) {

        if (produitInstance == null) {
            render status: NOT_FOUND
            return
        }

        produitInstance.delete flush:true
        render status: NO_CONTENT
    }
}
