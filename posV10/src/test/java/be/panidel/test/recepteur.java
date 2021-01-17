package be.panidel.test;

import java.net.*;
import java.io.*;

import javax.swing.*;

//import pour la jmf
import javax.media.*;
import javax.media.rtp.*;
import javax.media.rtp.event.*;
import javax.media.format.*;

import org.apache.log4j.Logger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;

//classe qui gere toute la reception

public class recepteur {

	private static final Logger log = Logger.getLogger("recepteur");

	private JPanel contentPanel;
	private JLabel label1;

	public static void main(String[] args) {
		new recepteur();
	}

	public recepteur() {

		JFrame mainfra = new JFrame();
		mainfra.setSize(200, 100);

		contentPanel = (JPanel) mainfra.getContentPane();
		contentPanel.setBackground(Color.white);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		label1 = new JLabel("En attente du flux video...");

		contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		contentPanel.add(label1);
		mainfra.setVisible(true);

		// Instanciation du RTPManager
		RTPManager VideoManager = RTPManager.newInstance();
		VideoManager.addFormat(new VideoFormat(VideoFormat.H263_RTP), 18);

		try {
			// Creation d'une SessionAddress pour l'adresse locale
			SessionAddress add = new SessionAddress(InetAddress.getLocalHost(),
					22224);

			// Initialisation du RTPManager
			// à partir de cette SessionAddress
			VideoManager.initialize(add);

			// Creation d'une SessionAddress pour l'adresse source
			SessionAddress add2 = new SessionAddress(
					InetAddress.getByName("localhost"), 85);

			// Ajout de cette SessionAddress dans le RTPManager
			VideoManager.addTarget(add2);
		}

		catch (InvalidSessionAddressException e) {
		}

		catch (IOException e) {
		}

		// Ajout du Listener de Reception de Stream dans le RTPManager (on
		// attend l'arrivee d'un flux)
		VideoManager.addReceiveStreamListener(new ReceiveStreamListener() {

			// Gestion de l'évènement ReceiveStreamEvent
			// Cette méthode se produit lorsque l'on reçoit un flux.
			// Il faut donc d'abord s'assurer que c'est un nouveau flux.
			// Ensuite, on récupère le DataSource de ce flux et on crée un
			// Player avec.
			// Enfin si, c'est un flux vidéo, on ajoute le composant visuel dans
			// une nouvelle Fenêtre créée.

			public void update(ReceiveStreamEvent event) {

				// Verification que l'event est un nouvel event
				if (event instanceof NewReceiveStreamEvent) {
					System.out.println("Reception d'un flux");

					// Nouveau Flux Recu obtenu
					ReceiveStream rs = event.getReceiveStream();

					try {
						// Creation du Player sur ce flux
						Player p = Manager.createRealizedPlayer(rs
								.getDataSource());

						// Si le player a un composant visuel,
						// alors creation d'une fenetre
						if (p.getVisualComponent() != null) {

							JFrame fenetre = new JFrame("Reception");
							fenetre.setSize(320, 280);
							fenetre.getContentPane()
									.add(p.getVisualComponent());
							fenetre.setVisible(true);
							fenetre.setLocation(100, 0);
						}

						// Demarrage du Player
						p.start();

						label1.setText("Flux video recu");
					} catch (HeadlessException e) {
						log.error("", e);
					} catch (NoPlayerException e) {
						log.error("", e);
					} catch (CannotRealizeException e) {
						log.error("", e);
					} catch (IOException e) {
						log.error("", e);
					}

				}

			}
		});

		System.out.println("Client demarre");

	}

}// class