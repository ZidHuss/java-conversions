package tech.zidhuss.converter;

public class Conversions {

    public static double cToF(double c) {
        return c * 9 / 5 + 32;
    }

    public static double fToC(double f) {
        return (f - 32) * 5 / 9;
    }

    public static double cToK(double c) {
        return c - 273.15;
    }

    public static double kToC(double k) {
        return k + 273.15;
    }

    public static double fToK(double f) {
        return cToK(fToC(f));
    }

    public static double kToF(double k) {
        return cToF(kToC(k));
    }

    public static double pToKg(double p) {
        return p / 2.2;
    }

    public static double kgToP(double kg) {
        return kg * 2.2;
    }
}
