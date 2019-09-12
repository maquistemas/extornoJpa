package pe.com.maquistemas.extornoJpa.oauth.services;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.stereotype.Service;

@Service
public class PlSqlService implements IPlSqlService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public String sp_transferencia(Integer PAR_TRANSF_USUARIO_ID, Integer PAR_TRANSF_CUENTA_ORIGEN, Integer PAR_TRANSF_CUENTA_DESTINO, BigDecimal PAR_TRANSF_MONTO) {
		
		Session session = em.unwrap(Session.class);
		CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
			@Override
			public CallableStatement execute(Connection connection) throws SQLException {
				CallableStatement function = connection.prepareCall("{ call PACK_TRANSACCION_MQ.SP_TRANSFERENCIA(?,?,?,?) }");
				function.setInt(1, PAR_TRANSF_USUARIO_ID);
				function.setInt(2, PAR_TRANSF_CUENTA_ORIGEN);
				function.setInt(3, PAR_TRANSF_CUENTA_DESTINO);
				function.setBigDecimal(4, PAR_TRANSF_MONTO);
				//register the OUT parameter before calling the stored procedure
				//function.registerOutParameter(3, Types.INTEGER);

				function.execute();
				
				//function.executeUpdate(); //para sp_insertar
				
				//read the OUT parameter now
//				Integer result = function.getInt(3);
//				System.out.println("resultado: "+result);
				
				return function;
			}
		});

		try {
			//return "" + callableStatement.getInt(3);
			return "La transferencia se realizó con exito";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

	@Override
	public String sp_extorno(Integer PAR_TRANSF_ID, Integer PAR_USUARIO_ID) {

		Session session = em.unwrap(Session.class);
		CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
			@Override
			public CallableStatement execute(Connection connection) throws SQLException {
				CallableStatement function = connection.prepareCall("{ call PACK_TRANSACCION_MQ.SP_EXTORNO(?,?) }");
				function.setInt(1, PAR_TRANSF_ID);
				function.setInt(2, PAR_USUARIO_ID);
				
				//register the OUT parameter before calling the stored procedure
				//function.registerOutParameter(3, Types.INTEGER);

				function.execute();
				
				//function.executeUpdate(); //para sp_insertar
				
				//read the OUT parameter now
//				Integer result = function.getInt(3);
//				System.out.println("resultado: "+result);
				
				return function;
			}
		});

		try {
			//return "" + callableStatement.getInt(3);
			return "El extorno se realizó con exito";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Integer sf_suma(Integer a, Integer b) {
		
		Session session = em.unwrap(Session.class);
		CallableStatement callableStatement = session.doReturningWork(new ReturningWork<CallableStatement>() {
			@Override
			public CallableStatement execute(Connection connection) throws SQLException {
				CallableStatement function = connection.prepareCall("{ ? = call PACK_TRANSACCION_MQ.SF_SUMA(?,?) }");
				function.registerOutParameter(1, Types.INTEGER);
				function.setInt(2, a);
				function.setInt(3, b);

				function.execute();
				return function;
			}
		});

		try {
			return callableStatement.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	
	}

}
