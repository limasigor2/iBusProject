package view;

import java.util.Scanner;

import com.google.gson.Gson;
import com.ibus.dao.OnibusDAO;

public class Main {
	
	
	
	public static void main(String [] args){
		OnibusDAO onibus = new OnibusDAO();
		Gson gson = new Gson();
		Scanner input = new Scanner(System.in);
		
		int operacao = 0;
		do{
			
			System.out.println("           MENU" +
							          "1. Onibus"
							        + "2. Usuario"
							      + "3. Administrador");
			
			operacao = input.nextInt();
			
			switch (operacao) {
			case 1:
				System.out.println("     Onibus" 
			                     + "1. Cadastrar Onibus"
						         + "2. Listar Onibus"
			                     + "3. Remover Onibus"
						         + "4. Atualizar Onibus");
				operacao = input.nextInt();
				
				switch (operacao) {
				case 1:
					System.out.println("Placa: (000 - AAAA)");
					String placa = input.nextLine();
					System.out.println("Cor:");
					String cor = input.nextLine();
					onibus.insertOnibus(objOnibus)
					break;

				default:
					break;
				}
				break;

			default:
				break;
			}
			
		}while(operacao == 0);
	}
}
