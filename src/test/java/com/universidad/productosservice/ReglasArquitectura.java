package com.universidad.productosservice;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.jpa.repository.JpaRepository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
		packages = "com.universidad.productosservice",
		importOptions = ImportOption.DoNotIncludeTests.class
)
class ReglasArquitectura {

	@ArchTest
	static final ArchRule dominioNoDependeDeCapasAplicacion = noClasses()
			.that().resideInAPackage("..domain..")
			.should().dependOnClassesThat()
			.resideInAnyPackage("..controller..", "..service..", "..repository..")
			.as("El dominio no debe depender de controller, service ni repository");

	@ArchTest
	static final ArchRule controladoresNoAccedenRepositorios = noClasses()
			.that().resideInAPackage("..controller..")
			.should().accessClassesThat()
			.resideInAPackage("..repository..")
			.as("Los controladores deben usar la capa service, no repository directamente");

	@ArchTest
	static final ArchRule controladoresSoloAccedenCapasPermitidas = classes()
			.that().resideInAPackage("..controller..")
			.should().onlyAccessClassesThat()
			.resideInAnyPackage(
					"..controller..",
					"..service..",
					"..domain..",
					"org.springframework..",
					"java.."
			)
			.as("Los controladores solo deben acceder a service, domain, Spring y Java");

	@ArchTest
	static final ArchRule contratosDeServicioSonInterfaces = classes()
			.that().resideInAPackage("..service..")
			.and().haveSimpleNameEndingWith("Service")
			.should().beInterfaces()
			.as("Los contratos de la capa service deben ser interfaces");

	@ArchTest
	static final ArchRule repositoriosSonInterfacesJpa = classes()
			.that().resideInAPackage("..repository..")
			.should().beInterfaces()
			.andShould().beAssignableTo(JpaRepository.class)
			.as("Los repositorios deben ser interfaces basadas en JpaRepository");

	private ReglasArquitectura() {
	}
}
