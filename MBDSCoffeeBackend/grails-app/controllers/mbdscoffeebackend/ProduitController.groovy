package mbdscoffeebackend

import com.Produit

class ProduitController {

    def index()
    {
        [produits : Produit.all]
    }

    def ajout()
    {
        Produit produit = new Produit()

    }
}
