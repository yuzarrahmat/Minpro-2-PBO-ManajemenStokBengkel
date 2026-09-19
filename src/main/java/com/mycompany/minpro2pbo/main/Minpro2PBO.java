package com.mycompany.minpro2pbo.main;

import com.mycompany.minpro2pbo.controller.BengkelController;
import com.mycompany.minpro2pbo.view.BengkelView;
import java.util.Scanner; 

public class Minpro2PBO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BengkelController controller = new BengkelController();
        BengkelView view = new BengkelView(controller, scanner);

        view.tampilkanMenuUtama();
    }
}