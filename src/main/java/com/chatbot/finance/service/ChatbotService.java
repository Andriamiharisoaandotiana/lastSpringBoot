package com.chatbot.finance.service;

import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class ChatbotService {

    // 🔥 Définition du chemin absolu du script Python
    private static final String PYTHON_SCRIPT_PATH = "E:/chatExemple/Nouveau dossier/Python_TraitementsPdf/scripts/chatbot.py";
    private static final String SCRIPT_DIRECTORY = "E:/chatExemple/Nouveau dossier/Python_TraitementsPdf/scripts";

    public String getResponse(String userQuestion) {
        try {
            // ✅ Vérifie si le fichier existe
            File scriptFile = new File(PYTHON_SCRIPT_PATH);
            if (!scriptFile.exists()) {
                return "{\"error\": \"Le script chatbot.py est introuvable.\"}";
            }

            // ✅ Définit le bon dossier d'exécution
            ProcessBuilder pb = new ProcessBuilder("python3", PYTHON_SCRIPT_PATH, userQuestion);
            pb.directory(new File(SCRIPT_DIRECTORY));
            pb.redirectErrorStream(true); // Capture erreurs et output

            // ✅ Démarrer le processus
            Process process = Runtime.getRuntime().exec(
                    "C:/Users/Andotiana/AppData/Local/Programs/Python/Python311/python.exe " +
                            "\"E:/chatExemple/Nouveau dossier/Python_TraitementsPdf/scripts/chatbot.py\" \"Quelle est la loi des finances en 2024 ?\""
            );

            //Process process = pb.start();

            // ✅ Lecture de la sortie du script Python en UTF-8
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // ✅ Attendre la fin du script
            int exitCode = process.waitFor();

            // ✅ Vérifier si le script s'est bien exécuté
            if (exitCode != 0) {
                return "{\"error\": \"Erreur lors de l'exécution du script Python. Code : " + exitCode + "\"}";
            }

            // ✅ Vérification de la réponse vide
            String response = output.toString().trim();
            if (response.isEmpty()) {
                return "{\"error\": \"Le chatbot n'a pas généré de réponse.\"}";
            }

            return response; // 🔥 Retour de la réponse du chatbot

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "{\"error\": \"Erreur interne du serveur : " + e.getMessage() + "\"}";
        }
    }
}
