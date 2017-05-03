class Acesso {
	
	private CalculaHorario calculaHorario = new CalculaHorario();

	public String placa;
	public int dia, mes, ano;
	public int horaEntrada, 
	           minutosEntrada;
	public int horaSaida, 
			   minutosSaida;
	
	public Acesso() {}
	
	public Acesso(int dia, int mes, int ano, int horaEntrada, int minutosEntrada) { 
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.horaEntrada = horaEntrada;
		this.minutosEntrada = minutosEntrada;
	}
	
		public float calcularValor() { 
		float valorTotal = calculaHorario.calcularValor(horaEntrada, horaSaida, minutosEntrada, minutosSaida);
		return valorTotal;
	}
		
	public void setHoraSaida(int horaSaida) {
		this.horaSaida = horaSaida;
	}

	public void setMinutosSaida(int minutosSaida) {
		this.minutosSaida = minutosSaida;
	}	
}

class CalculaHorario {

	private static final float VALOR_FRACAO = (float) 2.0;
	private static final float VALOR_HORA = (float) 7.0;
	private static final float VALOR_DIARIA = (float) 30.0;

	private int quantidadeHoras;
	private int quantidadeMinutos;

	public CalculaHorario() {}

	public float calcularValor(int horaEntrada, int horaSaida, int minutosEntrada, int minutosSaida) {
		int totalHoras = horaSaida - horaEntrada;
		int totalMinutos = minutosSaida - minutosEntrada;

		if(totalHoras >= 9) {
			return VALOR_DIARIA;
		} else {
			if (totalHoras == 0) {
				setHorasMinutos(totalHoras, totalMinutos);
			}
			else if (totalHoras > 0 && totalMinutos == 0) {
				setHorasMinutos(totalHoras, totalMinutos);
			}
			else if (totalHoras > 0 && totalMinutos > 1) {
				setHorasMinutos(totalHoras, totalMinutos);
			}
			else if (totalHoras > 0 && totalMinutos < 0){
				int minutosNegativos = minutosSaida + (60 - minutosEntrada);
				setHorasMinutos(totalHoras - 1, minutosNegativos);;
			}
			else {
				setHorasMinutos(0, 0);;
			}
			
			float valorTotal = calcularValorTotal();

			return valorTotal;
		}
	}

	public float getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public float getQuantidadeMinutos() {
		return quantidadeMinutos;
	}

	public void setQuantidadeHoras(int horas) {
		quantidadeHoras = horas;
	}

	public void setQuantidadeMinutos(int minutos) {
		quantidadeMinutos = minutos;
	}

	private float calcularValorTotal() {
		final float valorTotalHoras = getQuantidadeHoras() * VALOR_HORA;
		final float valorTotalMinutos = (float) Math.ceil(getQuantidadeMinutos() / 15.0) * VALOR_FRACAO;		
		final float valorTotal = valorTotalHoras + valorTotalMinutos; 
				
		return valorTotal;
	}

	private void setHorasMinutos(int horas, int minutos) {
		setQuantidadeHoras(horas);
		setQuantidadeMinutos(minutos);
	}
}