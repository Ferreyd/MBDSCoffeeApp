package com


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProduitController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Produit.list(params), model: [produitInstanceCount: Produit.count()]
    }

    def show(Produit produitInstance) {
        respond produitInstance
    }

    def create() {
        respond new Produit(params)
    }

    @Transactional
    def save(Produit produitInstance) {
        if (produitInstance == null) {
            notFound()
            return
        }

        if (produitInstance.hasErrors()) {
            respond produitInstance.errors, view: 'create'
            return
        }

        produitInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'produit.label', default: 'Produit'), produitInstance.id])
                redirect produitInstance
            }
            '*' { respond produitInstance, [status: CREATED] }
        }
    }

    def edit(Produit produitInstance) {
        respond produitInstance
    }

    @Transactional
    def update(Produit produitInstance) {
        if (produitInstance == null) {
            notFound()
            return
        }

        if (produitInstance.hasErrors()) {
            respond produitInstance.errors, view: 'edit'
            return
        }

        produitInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Produit.label', default: 'Produit'), produitInstance.id])
                redirect produitInstance
            }
            '*' { respond produitInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Produit produitInstance) {

        if (produitInstance == null) {
            notFound()
            return
        }

        produitInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Produit.label', default: 'Produit'), produitInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'produit.label', default: 'Produit'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
