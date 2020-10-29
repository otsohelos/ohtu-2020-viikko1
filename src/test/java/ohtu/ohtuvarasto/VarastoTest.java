package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

  Varasto varasto;
  Varasto saldovarasto;
  Varasto miinusvarasto;
  double vertailuTarkkuus = 0.0001;

  @Before
  public void setUp() {
    varasto = new Varasto(10);
    saldovarasto = new Varasto(10, 2);
    miinusvarasto = new Varasto(-2);
  }

  @Test
  public void konstruktoriLuoTyhjanVaraston() {
    assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void saldollisessaVarastossaOnSaldo() {
    assertEquals(2.0, saldovarasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void miinusvarastonKokoOnNolla() {
    assertEquals(0, miinusvarasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void uudellaVarastollaOikeaTilavuus() {
    assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
  }

  @Test
  public void lisaysLisaaSaldoa() {
    varasto.lisaaVarastoon(8);

    // saldon pitäisi olla sama kun lisätty määrä
    assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void lisaysLisaaPienentaaVapaataTilaa() {
    varasto.lisaaVarastoon(8);

    // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
    assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
  }

  @Test
  public void ottaminenPalauttaaOikeanMaaran() {
    varasto.lisaaVarastoon(8);

    double saatuMaara = varasto.otaVarastosta(2);

    assertEquals(2, saatuMaara, vertailuTarkkuus);
  }

  @Test
  public void liikaaOttaminenOttaaKaiken() {
    double saatuMaara = saldovarasto.otaVarastosta(3.0);

    assertEquals(2, saatuMaara, vertailuTarkkuus);
    assertEquals(0.0, saldovarasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void liikaaLisaaminenLisaaTayteen() {
    double liikaa = 11.0;

    varasto.lisaaVarastoon(liikaa);
    assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void miinuksenLisaaminenEiTeeMitaan() {
    saldovarasto.lisaaVarastoon(-2.0);
    assertEquals(2.0, saldovarasto.getSaldo(), vertailuTarkkuus);

  }

  @Test
  public void ottaminenLisääTilaa() {
    varasto.lisaaVarastoon(8);

    varasto.otaVarastosta(2);

    // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
    assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
  }

  @Test
  public void ylitaydenVarastonLuominenToimiiOikein() {
    Varasto ylitaysi = new Varasto(1.0, 2.0);
    assertEquals(1.0, ylitaysi.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void miinusvarastonLuominenTekeeNollavaraston() {
    Varasto miinusvarasto = new Varasto(-2.0, -2.0);
    assertEquals(0.0, miinusvarasto.getTilavuus(), vertailuTarkkuus);
    assertEquals(0.0, miinusvarasto.getSaldo(), vertailuTarkkuus);
  }

  @Test
  public void miinusmaaranOttaminenPalauttaaNolla() {
    double saatuMaara = saldovarasto.otaVarastosta(-2.0);

    assertEquals(0.0, saatuMaara, vertailuTarkkuus);
  }

  @Test
  public void toStringToimii() {
    String saatu = saldovarasto.toString();
    String haluttu = "saldo = 2.0, vielä tilaa 8.0";
    assertEquals(saatu, haluttu);
  }

}