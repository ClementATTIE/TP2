package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester
        

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        // S3 : on imprime pas le ticket si le montant inseré est insufisant
        public void notPrint(){
            assertEquals("la machine imprime le ticket meme quand le solde est insufisant",machine.printTicket(),false);
        }
        
        @Test
        //S4 : on imprime le ticket si le montant inséré est suffisant
        public void Print(){
            machine.insertMoney(machine.getPrice());
            assertEquals("avec le bon montant on imprime pas correctement",machine.printTicket(), true);
        }
        
        @Test
        //S5 :  Quand on imprime un ticket la balance est décrémentée du prix du ticket
        public void decrementCredit(){
            machine.insertMoney(machine.getPrice());
            machine.printTicket();
            assertEquals("on decremente mal suite a une impression", machine.getBalance(), 0);
        }
        
        @Test
        //S6 :le montant collecté est mis à jour quand on imprime un ticket (pas avant
        public void majMontantCollecté(){
            machine.insertMoney(machine.getPrice());
                assertEquals("le montant collecté change quand on insere des pièces",machine.getTotal(),0);
            machine.printTicket();
                assertEquals("Le montant collecté ne change pas quand on imprime le ticket",machine.getTotal(), machine.getPrice());
        }
        
        @Test
        //S7 : rend correctement la monnaie
        public void refundDone(){
            machine.insertMoney(machine.getPrice()+50);
            machine.printTicket();
            assertEquals("la machine ne rend pas la bonne somme",machine.refund(),50);
            assertEquals("la machine ne rend pas la bonne somme",machine.refund(),0);
        }
        
        
        @Test
        //S8 : remet la balance à zéro
        public void resetBalance(){
            machine.refund();
            assertEquals("ne remet pas la balance a zéro après un remboursement", machine.getBalance(),0);
        }

        @Test
        //S9 : on ne peut pas insérer un montant négatif
        public void montantNeg(){
            try{
            machine.insertMoney(-machine.getPrice());
            }
            catch (IllegalArgumentException e){
                System.out.println("on peut entrer un montant negatif");
            }
        }   

        @Test
        //S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
        public void PrixNeg(){
           try{
            TicketMachine machine2;
            machine2 = new TicketMachine(-PRICE);
            fail();
           }
           catch (IllegalArgumentException e){
              System.out.println("on peut creer une machine avec un prix negatif");
            }    
        }
}
