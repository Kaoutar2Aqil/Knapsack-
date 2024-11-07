package Code;

import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;
import ilog.cplex.IloCplex.UnknownObjectException;

public class sacados {
	double W;
	double [] pi;
    double [] wi;
    private int n ;
    
    IloCplex model; 
    IloNumVar [] x; 
    
    public sacados(double W,double [] pi,double [] wi) throws IloException {
    	
    	super();
    	this.W=W;
    	this.pi=pi;
    	this.wi=wi;
		n = pi.length;
		model=new IloCplex();
		creationModel();
    	
    }

	private void creationModel() throws IloException {
		creatvariables();
		creatConstraint();
		creatObject();
	}
	private void creatvariables() throws IloException {
		// TODO Auto-generated method stub
		x=model.boolVarArray(n);
		
		
		
	}
	private void creatConstraint() throws IloException {
		// TODO Auto-generated method stub
		IloLinearNumExpr lin =model.linearNumExpr();
		for(int i=0;i<n;i++) {
			lin.addTerm(x[i], wi[i]);
			}
		
		model.addLe(lin, W); //inferieur ou egale lin<=W  Le , sup Je
	}

	private void creatObject() throws IloException {
		// TODO Auto-generated method stub
		IloLinearNumExpr lin =model.linearNumExpr();
		for(int i=0;i<n;i++) {
			lin.addTerm(x[i], pi[i]);
			
		}
		model.addMaximize(lin); //MAX
	}

	public boolean solve() throws IloException {
    	
    		return model.solve();
    }
	
	
    public double [] getX() throws UnknownObjectException, IloException {
    	double[] d= new double[n];
    	
    	if (solve()) {d=model.getValues(x);
    				}
		return d;
    	
    	
    }
    
    
    public void getSolutionSac_Dos() throws UnknownObjectException, IloException {
    	
    	double [] d= getX();
    	
        
    	for(int i=0;i<d.length;i++) {
    		if(d[i]==1)
    			System.out.println("objet " +(i+1));
    		
    	}
        }
    
    
	public static void main(String[] args) throws IloException {
		double [] p= {4,2,1,10,2};
		double [] w= {12,2,1,4,1};
		
		sacados s1 =new sacados(15,w,p);
		s1.getSolutionSac_Dos();
	}
}
