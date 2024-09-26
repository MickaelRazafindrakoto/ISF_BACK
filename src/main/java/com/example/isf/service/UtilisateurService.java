package com.example.isf.service;

import com.example.isf.model.Personne;
import com.example.isf.model.Utilisateur;
import com.example.isf.repository.PersonneRepository;
import com.example.isf.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PersonneRepository personneRepository;

    public Optional<Utilisateur> select_utilisateur_by_Personne_Pswd(int id_personne , String pswd) {
        return this.utilisateurRepository.select_utilisateur_by_Personne_Pswd(id_personne , pswd);
    }
    public Utilisateur login(String nom , String pswd) throws Exception {
        Optional<Personne> personne = this.personneRepository.select_personne_by_Email(nom);

        if (personne.isPresent()){
            Optional<Utilisateur> utilisateur = this.select_utilisateur_by_Personne_Pswd(personne.get().getId_personne() , pswd);
            if (utilisateur.isPresent()){
                return utilisateur.get();
            }
            else {
                throw new Exception("Mot de passe incorrect");
            }
        }
        else {
            throw new Exception("User incorrect");
        }
    }

    public Utilisateur Enregistrer(Utilisateur utilisateur){
        return this.utilisateurRepository.save(utilisateur);
    }
}
