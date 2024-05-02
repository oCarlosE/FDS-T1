package com.bank.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

    private ContaMagica conta;

    @BeforeEach
    public void setUp() {
        conta = new ContaMagica();
    }

    @Test
    public void testDepositoComValorPositivo() {
        conta.Deposito(1000.0);
        assertEquals(1000.0, conta.getSaldo(), 0.001);
    }

    @Test
    public void testDepositoComValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            conta.Deposito(-500.0);
        });
    }

    @Test
    public void testSaqueComValorValido() {
        conta.Deposito(1000.0);
        double saque = conta.Saque(500.0);
        assertEquals(500.0, saque, 0.001);
        assertEquals(500.0, conta.getSaldo(), 0.001);
    }

    @Test
    public void testSaqueComValorMaiorQueSaldo() {
        conta.Deposito(300.0);
        assertThrows(IllegalArgumentException.class, () -> {
            conta.Saque(500.0);
        });
    }

    @Test
    public void testSaqueComValorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            conta.Saque(-100.0);
        });
    }

	@Test
	public void testUpgradeParaGold() {
		conta.Deposito(50000.0);
		assertEquals(ContaMagica.GOLD, conta.getStatus());
	}

	@Test
	public void testUpgradeParaPlatinum() {
    conta.Deposito(50000.0);
    conta.Deposito(200000.0);
    assertEquals(ContaMagica.PLATINUM, conta.getStatus());
	}


    @Test
    public void testDowngradeParaSilver() {
        conta.Deposito(50000.0);
        conta.Saque(30000.0);
        assertEquals(ContaMagica.SILVER, conta.getStatus());
    }

    @Test
    public void testDowngradeParaGold(){
        conta.Deposito(50000.0);
        conta.Deposito(150000.0);
        conta.Saque(150000.0);
        assertEquals(ContaMagica.GOLD, conta.getStatus());
    }

    @Test
    public void testGetStatus() {
        assertEquals(ContaMagica.SILVER, conta.getStatus());
    }

    @Test
    public void testGetSaldo() {
        conta.Deposito(1000.0);
        assertEquals(1000.0, conta.getSaldo(), 0.001);
    }

	@Test
	public void testDepositoEmContaSilver() {
		conta.Deposito(1000.0);
		assertEquals(1000.0, conta.getSaldo(), 0.001);
	}

	@Test
	public void testRetiradaComDowngradePlatinumParaGold() {
		conta.Deposito(200000.0); 
		conta.Saque(100000.0); 
		assertEquals(ContaMagica.GOLD, conta.getStatus());
	}

	@Test
	public void testRetiradaComDowngradeGoldParaSilver() {
		conta.Deposito(50000.0);
		conta.Saque(30000.0); 
		assertEquals(ContaMagica.SILVER, conta.getStatus());
	}

	@Test
	public void testNaoUpgradeDiretoDeSilverParaPlatinum() {
		conta.Deposito(100000.0);
		assertEquals(ContaMagica.GOLD, conta.getStatus());
	}
	

	@Test
	public void testNaoDowngradeDuplo() {
		conta.Deposito(200000.0);
		conta.Saque(150000.0); 
		assertEquals(ContaMagica.GOLD, conta.getStatus());
	}

	@Test
	public void testLimiteInferiorParaDowngradeSilver() {
		conta.Deposito(50000.0);
		conta.Saque(25000.1);  
		assertEquals(ContaMagica.SILVER, conta.getStatus());
	}

	@Test
	public void testLimiteSuperiorParaDowngradeGold() {
		conta.Deposito(50000.0); 
		conta.Deposito(150000.0); 
		conta.Saque(150000.0); 
		assertEquals(ContaMagica.GOLD, conta.getStatus());
	}
}
