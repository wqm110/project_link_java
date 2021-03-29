package com.ruoyi.camunda.plugin;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.impl.DefaultDmnEngineConfiguration;
import org.camunda.bpm.dmn.feel.impl.scala.function.FeelCustomFunctionProvider;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.authorization.Permission;
import org.camunda.bpm.engine.identity.PasswordPolicy;
import org.camunda.bpm.engine.impl.HistoryLevelSetupCommand;
import org.camunda.bpm.engine.impl.OptimizeService;
import org.camunda.bpm.engine.impl.PriorityProvider;
import org.camunda.bpm.engine.impl.ProcessEngineImpl;
import org.camunda.bpm.engine.impl.application.ProcessApplicationManager;
import org.camunda.bpm.engine.impl.batch.BatchJobHandler;
import org.camunda.bpm.engine.impl.bpmn.behavior.ExternalTaskActivityBehavior;
import org.camunda.bpm.engine.impl.bpmn.deployer.BpmnDeployer;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParseListener;
import org.camunda.bpm.engine.impl.calendar.BusinessCalendarManager;
import org.camunda.bpm.engine.impl.cfg.*;
import org.camunda.bpm.engine.impl.cfg.auth.PermissionProvider;
import org.camunda.bpm.engine.impl.cfg.auth.ResourceAuthorizationProvider;
import org.camunda.bpm.engine.impl.cfg.multitenancy.TenantIdProvider;
import org.camunda.bpm.engine.impl.cmmn.deployer.CmmnDeployer;
import org.camunda.bpm.engine.impl.cmmn.transformer.CmmnTransformListener;
import org.camunda.bpm.engine.impl.db.entitymanager.cache.DbEntityCacheKeyMapping;
import org.camunda.bpm.engine.impl.db.sql.DbSqlSessionFactory;
import org.camunda.bpm.engine.impl.digest.PasswordEncryptor;
import org.camunda.bpm.engine.impl.digest.PasswordManager;
import org.camunda.bpm.engine.impl.digest.SaltGenerator;
import org.camunda.bpm.engine.impl.dmn.deployer.DecisionDefinitionDeployer;
import org.camunda.bpm.engine.impl.dmn.deployer.DecisionRequirementsDefinitionDeployer;
import org.camunda.bpm.engine.impl.el.ExpressionManager;
import org.camunda.bpm.engine.impl.event.EventHandler;
import org.camunda.bpm.engine.impl.form.engine.FormEngine;
import org.camunda.bpm.engine.impl.form.type.AbstractFormFieldType;
import org.camunda.bpm.engine.impl.form.type.FormTypes;
import org.camunda.bpm.engine.impl.form.validator.FormFieldValidator;
import org.camunda.bpm.engine.impl.form.validator.FormValidators;
import org.camunda.bpm.engine.impl.history.HistoryLevel;
import org.camunda.bpm.engine.impl.history.HistoryRemovalTimeProvider;
import org.camunda.bpm.engine.impl.history.event.HostnameProvider;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.camunda.bpm.engine.impl.history.producer.CmmnHistoryEventProducer;
import org.camunda.bpm.engine.impl.history.producer.DmnHistoryEventProducer;
import org.camunda.bpm.engine.impl.history.producer.HistoryEventProducer;
import org.camunda.bpm.engine.impl.incident.IncidentHandler;
import org.camunda.bpm.engine.impl.interceptor.*;
import org.camunda.bpm.engine.impl.jobexecutor.*;
import org.camunda.bpm.engine.impl.jobexecutor.historycleanup.BatchWindowManager;
import org.camunda.bpm.engine.impl.metrics.MetricsRegistry;
import org.camunda.bpm.engine.impl.metrics.MetricsReporterIdProvider;
import org.camunda.bpm.engine.impl.metrics.reporter.DbMetricsReporter;
import org.camunda.bpm.engine.impl.migration.MigrationActivityMatcher;
import org.camunda.bpm.engine.impl.migration.MigrationInstructionGenerator;
import org.camunda.bpm.engine.impl.migration.validation.activity.MigrationActivityValidator;
import org.camunda.bpm.engine.impl.migration.validation.instance.MigratingActivityInstanceValidator;
import org.camunda.bpm.engine.impl.migration.validation.instance.MigratingCompensationInstanceValidator;
import org.camunda.bpm.engine.impl.migration.validation.instance.MigratingTransitionInstanceValidator;
import org.camunda.bpm.engine.impl.migration.validation.instruction.MigrationInstructionValidator;
import org.camunda.bpm.engine.impl.persistence.deploy.Deployer;
import org.camunda.bpm.engine.impl.persistence.deploy.cache.CacheFactory;
import org.camunda.bpm.engine.impl.persistence.deploy.cache.DeploymentCache;
import org.camunda.bpm.engine.impl.runtime.ConditionHandler;
import org.camunda.bpm.engine.impl.runtime.CorrelationHandler;
import org.camunda.bpm.engine.impl.scripting.ScriptFactory;
import org.camunda.bpm.engine.impl.scripting.engine.ResolverFactory;
import org.camunda.bpm.engine.impl.scripting.engine.ScriptingEngines;
import org.camunda.bpm.engine.impl.scripting.env.ScriptEnvResolver;
import org.camunda.bpm.engine.impl.scripting.env.ScriptingEnvironment;
import org.camunda.bpm.engine.impl.telemetry.TelemetryRegistry;
import org.camunda.bpm.engine.impl.telemetry.dto.Data;
import org.camunda.bpm.engine.impl.telemetry.reporter.TelemetryReporter;
import org.camunda.bpm.engine.impl.variable.serializer.TypedValueSerializer;
import org.camunda.bpm.engine.impl.variable.serializer.VariableSerializerFactory;
import org.camunda.bpm.engine.impl.variable.serializer.VariableSerializers;
import org.camunda.bpm.engine.repository.DeploymentHandlerFactory;
import org.camunda.bpm.engine.runtime.DeserializationTypeValidator;
import org.camunda.bpm.engine.variable.type.ValueTypeResolver;
import org.camunda.connect.spi.Connector;
import org.camunda.connect.spi.ConnectorRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.*;

public class ProcessEnginePlugInTest2 extends StandaloneProcessEngineConfiguration {
    public ProcessEnginePlugInTest2() {
        super();
        System.out.println("。。。。。。。。。。。。。。。。。。。。Camunda 配置扩展。。。。。。。。。。。。。。。。。。。。");
    }

    @Override
    protected void initArtifactFactory() {
        super.initArtifactFactory();
    }



    @Override
    protected void initScripting() {
        super.initScripting();
    }

    @Override
    public ProcessEngine buildProcessEngine() {
        return super.buildProcessEngine();
    }

    @Override
    protected Collection<? extends CommandInterceptor> getDefaultCommandInterceptorsTxRequired() {
        return super.getDefaultCommandInterceptorsTxRequired();
    }

    @Override
    protected Collection<? extends CommandInterceptor> getDefaultCommandInterceptorsTxRequiresNew() {
        return super.getDefaultCommandInterceptorsTxRequiresNew();
    }

    @Override
    protected void initTransactionContextFactory() {
        super.initTransactionContextFactory();
    }

    @Override
    protected void initJpa() {
        super.initJpa();
    }



//    @Override
//    public ProcessEngineConfigurationImpl setDataSource(DataSource dataSource) {
//        return super.setDataSource(dataSource);
//    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void initTypeValidator() {
        super.initTypeValidator();
    }

    @Override
    public void initHistoryRemovalTime() {
        super.initHistoryRemovalTime();
    }

    @Override
    public void initHistoryRemovalTimeStrategy() {
        super.initHistoryRemovalTimeStrategy();
    }

    @Override
    public void initHistoryRemovalTimeProvider() {
        super.initHistoryRemovalTimeProvider();
    }

    @Override
    public void initHistoryCleanup() {
        super.initHistoryCleanup();
    }

    @Override
    protected void initHistoryCleanupStrategy() {
        super.initHistoryCleanupStrategy();
    }

    @Override
    protected void initInvocationsPerBatchJobByBatchType() {
        super.initInvocationsPerBatchJobByBatchType();
    }

    @Override
    protected void initHistoryTimeToLive() {
        super.initHistoryTimeToLive();
    }

    @Override
    protected void initBatchOperationsHistoryTimeToLive() {
        super.initBatchOperationsHistoryTimeToLive();
    }

    @Override
    protected void initHistoryCleanupJobLogTimeToLive() {
        super.initHistoryCleanupJobLogTimeToLive();
    }

    @Override
    protected void invokePreInit() {
        super.invokePreInit();
    }

    @Override
    protected void invokePostInit() {
        super.invokePostInit();
    }

    @Override
    protected void invokePostProcessEngineBuild(ProcessEngine engine) {
        super.invokePostProcessEngineBuild(engine);
    }

    @Override
    protected void initFailedJobCommandFactory() {
        super.initFailedJobCommandFactory();
    }

    @Override
    protected void initIncidentHandlers() {
        super.initIncidentHandlers();
    }

    @Override
    protected void initBatchHandlers() {
        super.initBatchHandlers();
    }

    @Override
    protected void initCommandExecutors() {
        super.initCommandExecutors();
    }

    @Override
    protected void initActualCommandExecutor() {
        super.initActualCommandExecutor();
    }

    @Override
    protected void initCommandInterceptorsTxRequired() {
        super.initCommandInterceptorsTxRequired();
    }

    @Override
    protected void initCommandInterceptorsTxRequiresNew() {
        super.initCommandInterceptorsTxRequiresNew();
    }

    @Override
    protected void initCommandExecutorTxRequired() {
        super.initCommandExecutorTxRequired();
    }

    @Override
    protected void initCommandExecutorTxRequiresNew() {
        super.initCommandExecutorTxRequiresNew();
    }

    @Override
    protected void initCommandExecutorDbSchemaOperations() {
        super.initCommandExecutorDbSchemaOperations();
    }

    @Override
    protected CommandInterceptor initInterceptorChain(List<CommandInterceptor> chain) {
        return super.initInterceptorChain(chain);
    }

    @Override
    protected void initServices() {
        super.initServices();
    }

    @Override
    protected void initService(Object service) {
        super.initService(service);
    }

//    @Override
//    protected void initDataSource() {
//        super.initDataSource();
//    }

    @Override
    public void initDatabaseType() {
        super.initDatabaseType();
    }

    /**
     * The product name of mariadb is still 'MySQL'. This method
     * tries if it can find some evidence for mariadb. If it is successful
     * it will return "MariaDB", otherwise the provided database name.
     *
     * @param databaseMetaData
     * @param databaseName
     */
    @Override
    protected String checkForMariaDb(DatabaseMetaData databaseMetaData, String databaseName) {
        return super.checkForMariaDb(databaseMetaData, databaseName);
    }

    @Override
    protected String checkForCrdb(Connection connection) {
        return super.checkForCrdb(connection);
    }

    @Override
    protected void initDatabaseVendorAndVersion(DatabaseMetaData databaseMetaData) throws SQLException {
        super.initDatabaseVendorAndVersion(databaseMetaData);
    }

    @Override
    protected void initTransactionFactory() {
        super.initTransactionFactory();
    }

    @Override
    protected void initSqlSessionFactory() {
        super.initSqlSessionFactory();
    }

    @Override
    protected InputStream getMyBatisXmlConfigurationSteam() {
        return super.getMyBatisXmlConfigurationSteam();
    }

    @Override
    protected void initIdentityProviderSessionFactory() {
        super.initIdentityProviderSessionFactory();
    }

    @Override
    protected void initSessionFactories() {
        super.initSessionFactories();
    }

    @Override
    protected void initPersistenceProviders() {
        super.initPersistenceProviders();
    }

    @Override
    protected void initMigration() {
        super.initMigration();
    }

    @Override
    protected void initMigrationActivityMatcher() {
        super.initMigrationActivityMatcher();
    }

    @Override
    protected void initMigrationInstructionGenerator() {
        super.initMigrationInstructionGenerator();
    }

    @Override
    protected void initMigrationInstructionValidators() {
        super.initMigrationInstructionValidators();
    }

    @Override
    protected void initMigratingActivityInstanceValidators() {
        super.initMigratingActivityInstanceValidators();
    }

    @Override
    protected void initMigratingTransitionInstanceValidators() {
        super.initMigratingTransitionInstanceValidators();
    }

    @Override
    protected void initMigratingCompensationInstanceValidators() {
        super.initMigratingCompensationInstanceValidators();
    }

    /**
     * When providing a schema and a prefix  the prefix has to be the schema ending with a dot.
     *
     * @param prefix
     * @param schema
     */
    @Override
    protected void ensurePrefixAndSchemaFitToegether(String prefix, String schema) {
        super.ensurePrefixAndSchemaFitToegether(prefix, schema);
    }

    @Override
    protected void addSessionFactory(SessionFactory sessionFactory) {
        super.addSessionFactory(sessionFactory);
    }

    @Override
    protected void initDeployers() {
        super.initDeployers();
    }

    @Override
    protected Collection<? extends Deployer> getDefaultDeployers() {
        return super.getDefaultDeployers();
    }

    @Override
    protected BpmnDeployer getBpmnDeployer() {
        return super.getBpmnDeployer();
    }

    @Override
    protected List<BpmnParseListener> getDefaultBPMNParseListeners() {
        return super.getDefaultBPMNParseListeners();
    }

    @Override
    protected CmmnDeployer getCmmnDeployer() {
        return super.getCmmnDeployer();
    }

    @Override
    protected List<CmmnTransformListener> getDefaultCmmnTransformListeners() {
        return super.getDefaultCmmnTransformListeners();
    }

    @Override
    protected DecisionDefinitionDeployer getDecisionDefinitionDeployer() {
        return super.getDecisionDefinitionDeployer();
    }

    @Override
    protected DecisionRequirementsDefinitionDeployer getDecisionRequirementsDefinitionDeployer() {
        return super.getDecisionRequirementsDefinitionDeployer();
    }

    @Override
    public DmnEngine getDmnEngine() {
        return super.getDmnEngine();
    }

    @Override
    public void setDmnEngine(DmnEngine dmnEngine) {
        super.setDmnEngine(dmnEngine);
    }

    @Override
    public DefaultDmnEngineConfiguration getDmnEngineConfiguration() {
        return super.getDmnEngineConfiguration();
    }

    @Override
    public void setDmnEngineConfiguration(DefaultDmnEngineConfiguration dmnEngineConfiguration) {
        super.setDmnEngineConfiguration(dmnEngineConfiguration);
    }

    @Override
    protected void initJobExecutor() {
        super.initJobExecutor();
    }

    @Override
    protected void initJobProvider() {
        super.initJobProvider();
    }

    @Override
    protected void initExternalTaskPriorityProvider() {
        super.initExternalTaskPriorityProvider();
    }

    @Override
    public void initHistoryLevel() {
        super.initHistoryLevel();
    }

    @Override
    protected void initIdGenerator() {
        super.initIdGenerator();
    }

    @Override
    protected void initCommandContextFactory() {
        super.initCommandContextFactory();
    }

    @Override
    protected void initValueTypeResolver() {
        super.initValueTypeResolver();
    }

    @Override
    protected void initDefaultCharset() {
        super.initDefaultCharset();
    }

    @Override
    protected void initMetrics() {
        super.initMetrics();
    }

    @Override
    protected void initHostName() {
        super.initHostName();
    }

    @Override
    protected void initDefaultMetrics(MetricsRegistry metricsRegistry) {
        super.initDefaultMetrics(metricsRegistry);
    }

    @Override
    protected void initSerialization() {
        super.initSerialization();
    }

    @Override
    protected void initFormEngines() {
        super.initFormEngines();
    }

    @Override
    protected void initFormTypes() {
        super.initFormTypes();
    }

    @Override
    protected void initFormFieldValidators() {
        super.initFormFieldValidators();
    }

    @Override
    protected void initDmnEngine() {
        super.initDmnEngine();
    }

    @Override
    protected void initExpressionManager() {
        super.initExpressionManager();
    }

    @Override
    protected void initBusinessCalendarManager() {
        super.initBusinessCalendarManager();
    }

    @Override
    protected void initDelegateInterceptor() {
        super.initDelegateInterceptor();
    }

    @Override
    protected void initEventHandlers() {
        super.initEventHandlers();
    }

    @Override
    protected void initCommandCheckers() {
        super.initCommandCheckers();
    }

    @Override
    protected void initBeans() {
        super.initBeans();
    }

    @Override
    protected void initProcessApplicationManager() {
        super.initProcessApplicationManager();
    }

    @Override
    protected void initCorrelationHandler() {
        super.initCorrelationHandler();
    }

    @Override
    protected void initConditionHandler() {
        super.initConditionHandler();
    }

    @Override
    protected void initDeploymentHandlerFactory() {
        super.initDeploymentHandlerFactory();
    }

    @Override
    protected void initHistoryEventProducer() {
        super.initHistoryEventProducer();
    }

    @Override
    protected void initCmmnHistoryEventProducer() {
        super.initCmmnHistoryEventProducer();
    }

    @Override
    protected void initDmnHistoryEventProducer() {
        super.initDmnHistoryEventProducer();
    }

    @Override
    protected void initHistoryEventHandler() {
        super.initHistoryEventHandler();
    }

    @Override
    protected void initPasswordDigest() {
        super.initPasswordDigest();
    }

    @Override
    public void initPasswordPolicy() {
        super.initPasswordPolicy();
    }

    @Override
    protected void initDeploymentRegistration() {
        super.initDeploymentRegistration();
    }

    @Override
    protected void initCacheFactory() {
        super.initCacheFactory();
    }

    @Override
    protected void initResourceAuthorizationProvider() {
        super.initResourceAuthorizationProvider();
    }

    @Override
    protected void initPermissionProvider() {
        super.initPermissionProvider();
    }

    @Override
    protected void initDefaultUserPermissionForTask() {
        super.initDefaultUserPermissionForTask();
    }

    @Override
    protected void initAdminUser() {
        super.initAdminUser();
    }

    @Override
    protected void initAdminGroups() {
        super.initAdminGroups();
    }

    @Override
    protected void initTelemetry() {
        super.initTelemetry();
    }

    @Override
    protected void initTelemetryData() {
        super.initTelemetryData();
    }

    @Override
    public String getProcessEngineName() {
        return super.getProcessEngineName();
    }

    @Override
    public HistoryLevel getHistoryLevel() {
        return super.getHistoryLevel();
    }

    @Override
    public void setHistoryLevel(HistoryLevel historyLevel) {
        super.setHistoryLevel(historyLevel);
    }

    @Override
    public HistoryLevel getDefaultHistoryLevel() {
        return super.getDefaultHistoryLevel();
    }

    @Override
    public ProcessEngineConfigurationImpl setProcessEngineName(String processEngineName) {
        return super.setProcessEngineName(processEngineName);
    }

    @Override
    public List<CommandInterceptor> getCustomPreCommandInterceptorsTxRequired() {
        return super.getCustomPreCommandInterceptorsTxRequired();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomPreCommandInterceptorsTxRequired(List<CommandInterceptor> customPreCommandInterceptorsTxRequired) {
        return super.setCustomPreCommandInterceptorsTxRequired(customPreCommandInterceptorsTxRequired);
    }

    @Override
    public List<CommandInterceptor> getCustomPostCommandInterceptorsTxRequired() {
        return super.getCustomPostCommandInterceptorsTxRequired();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomPostCommandInterceptorsTxRequired(List<CommandInterceptor> customPostCommandInterceptorsTxRequired) {
        return super.setCustomPostCommandInterceptorsTxRequired(customPostCommandInterceptorsTxRequired);
    }

    @Override
    public List<CommandInterceptor> getCommandInterceptorsTxRequired() {
        return super.getCommandInterceptorsTxRequired();
    }

    @Override
    public ProcessEngineConfigurationImpl setCommandInterceptorsTxRequired(List<CommandInterceptor> commandInterceptorsTxRequired) {
        return super.setCommandInterceptorsTxRequired(commandInterceptorsTxRequired);
    }

    @Override
    public CommandExecutor getCommandExecutorTxRequired() {
        return super.getCommandExecutorTxRequired();
    }

    @Override
    public ProcessEngineConfigurationImpl setCommandExecutorTxRequired(CommandExecutor commandExecutorTxRequired) {
        return super.setCommandExecutorTxRequired(commandExecutorTxRequired);
    }

    @Override
    public List<CommandInterceptor> getCustomPreCommandInterceptorsTxRequiresNew() {
        return super.getCustomPreCommandInterceptorsTxRequiresNew();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomPreCommandInterceptorsTxRequiresNew(List<CommandInterceptor> customPreCommandInterceptorsTxRequiresNew) {
        return super.setCustomPreCommandInterceptorsTxRequiresNew(customPreCommandInterceptorsTxRequiresNew);
    }

    @Override
    public List<CommandInterceptor> getCustomPostCommandInterceptorsTxRequiresNew() {
        return super.getCustomPostCommandInterceptorsTxRequiresNew();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomPostCommandInterceptorsTxRequiresNew(List<CommandInterceptor> customPostCommandInterceptorsTxRequiresNew) {
        return super.setCustomPostCommandInterceptorsTxRequiresNew(customPostCommandInterceptorsTxRequiresNew);
    }

    @Override
    public List<CommandInterceptor> getCommandInterceptorsTxRequiresNew() {
        return super.getCommandInterceptorsTxRequiresNew();
    }

    @Override
    public ProcessEngineConfigurationImpl setCommandInterceptorsTxRequiresNew(List<CommandInterceptor> commandInterceptorsTxRequiresNew) {
        return super.setCommandInterceptorsTxRequiresNew(commandInterceptorsTxRequiresNew);
    }

    @Override
    public CommandExecutor getCommandExecutorTxRequiresNew() {
        return super.getCommandExecutorTxRequiresNew();
    }

    @Override
    public ProcessEngineConfigurationImpl setCommandExecutorTxRequiresNew(CommandExecutor commandExecutorTxRequiresNew) {
        return super.setCommandExecutorTxRequiresNew(commandExecutorTxRequiresNew);
    }

    @Override
    public RepositoryService getRepositoryService() {
        return super.getRepositoryService();
    }

    @Override
    public ProcessEngineConfigurationImpl setRepositoryService(RepositoryService repositoryService) {
        return super.setRepositoryService(repositoryService);
    }

    @Override
    public RuntimeService getRuntimeService() {
        return super.getRuntimeService();
    }

    @Override
    public ProcessEngineConfigurationImpl setRuntimeService(RuntimeService runtimeService) {
        return super.setRuntimeService(runtimeService);
    }

    @Override
    public HistoryService getHistoryService() {
        return super.getHistoryService();
    }

    @Override
    public ProcessEngineConfigurationImpl setHistoryService(HistoryService historyService) {
        return super.setHistoryService(historyService);
    }

    @Override
    public IdentityService getIdentityService() {
        return super.getIdentityService();
    }

    @Override
    public ProcessEngineConfigurationImpl setIdentityService(IdentityService identityService) {
        return super.setIdentityService(identityService);
    }

    @Override
    public TaskService getTaskService() {
        return super.getTaskService();
    }

    @Override
    public ProcessEngineConfigurationImpl setTaskService(TaskService taskService) {
        return super.setTaskService(taskService);
    }

    @Override
    public FormService getFormService() {
        return super.getFormService();
    }

    @Override
    public ProcessEngineConfigurationImpl setFormService(FormService formService) {
        return super.setFormService(formService);
    }

    @Override
    public ManagementService getManagementService() {
        return super.getManagementService();
    }

    @Override
    public AuthorizationService getAuthorizationService() {
        return super.getAuthorizationService();
    }

    @Override
    public void setAuthorizationService(AuthorizationService authorizationService) {
        super.setAuthorizationService(authorizationService);
    }

    @Override
    public ProcessEngineConfigurationImpl setManagementService(ManagementService managementService) {
        return super.setManagementService(managementService);
    }

    @Override
    public CaseService getCaseService() {
        return super.getCaseService();
    }

    @Override
    public void setCaseService(CaseService caseService) {
        super.setCaseService(caseService);
    }

    @Override
    public FilterService getFilterService() {
        return super.getFilterService();
    }

    @Override
    public void setFilterService(FilterService filterService) {
        super.setFilterService(filterService);
    }

    @Override
    public ExternalTaskService getExternalTaskService() {
        return super.getExternalTaskService();
    }

    @Override
    public void setExternalTaskService(ExternalTaskService externalTaskService) {
        super.setExternalTaskService(externalTaskService);
    }

    @Override
    public DecisionService getDecisionService() {
        return super.getDecisionService();
    }

    @Override
    public OptimizeService getOptimizeService() {
        return super.getOptimizeService();
    }

    @Override
    public void setDecisionService(DecisionService decisionService) {
        super.setDecisionService(decisionService);
    }

    @Override
    public Map<Class<?>, SessionFactory> getSessionFactories() {
        return super.getSessionFactories();
    }

    @Override
    public ProcessEngineConfigurationImpl setSessionFactories(Map<Class<?>, SessionFactory> sessionFactories) {
        return super.setSessionFactories(sessionFactories);
    }

    @Override
    public List<Deployer> getDeployers() {
        return super.getDeployers();
    }

    @Override
    public ProcessEngineConfigurationImpl setDeployers(List<Deployer> deployers) {
        return super.setDeployers(deployers);
    }

    @Override
    public JobExecutor getJobExecutor() {
        return super.getJobExecutor();
    }

    @Override
    public ProcessEngineConfigurationImpl setJobExecutor(JobExecutor jobExecutor) {
        return super.setJobExecutor(jobExecutor);
    }

    @Override
    public PriorityProvider<JobDeclaration<?, ?>> getJobPriorityProvider() {
        return super.getJobPriorityProvider();
    }

    @Override
    public void setJobPriorityProvider(PriorityProvider<JobDeclaration<?, ?>> jobPriorityProvider) {
        super.setJobPriorityProvider(jobPriorityProvider);
    }

    @Override
    public PriorityProvider<ExternalTaskActivityBehavior> getExternalTaskPriorityProvider() {
        return super.getExternalTaskPriorityProvider();
    }

    @Override
    public void setExternalTaskPriorityProvider(PriorityProvider<ExternalTaskActivityBehavior> externalTaskPriorityProvider) {
        super.setExternalTaskPriorityProvider(externalTaskPriorityProvider);
    }

    @Override
    public IdGenerator getIdGenerator() {
        return super.getIdGenerator();
    }

    @Override
    public ProcessEngineConfigurationImpl setIdGenerator(IdGenerator idGenerator) {
        return super.setIdGenerator(idGenerator);
    }

    @Override
    public String getWsSyncFactoryClassName() {
        return super.getWsSyncFactoryClassName();
    }

    @Override
    public ProcessEngineConfigurationImpl setWsSyncFactoryClassName(String wsSyncFactoryClassName) {
        return super.setWsSyncFactoryClassName(wsSyncFactoryClassName);
    }

    @Override
    public Map<String, FormEngine> getFormEngines() {
        return super.getFormEngines();
    }

    @Override
    public ProcessEngineConfigurationImpl setFormEngines(Map<String, FormEngine> formEngines) {
        return super.setFormEngines(formEngines);
    }

    @Override
    public FormTypes getFormTypes() {
        return super.getFormTypes();
    }

    @Override
    public ProcessEngineConfigurationImpl setFormTypes(FormTypes formTypes) {
        return super.setFormTypes(formTypes);
    }

    @Override
    public ScriptingEngines getScriptingEngines() {
        return super.getScriptingEngines();
    }

    @Override
    public ProcessEngineConfigurationImpl setScriptingEngines(ScriptingEngines scriptingEngines) {
        return super.setScriptingEngines(scriptingEngines);
    }

    @Override
    public VariableSerializers getVariableSerializers() {
        return super.getVariableSerializers();
    }

    @Override
    public VariableSerializerFactory getFallbackSerializerFactory() {
        return super.getFallbackSerializerFactory();
    }

    @Override
    public void setFallbackSerializerFactory(VariableSerializerFactory fallbackSerializerFactory) {
        super.setFallbackSerializerFactory(fallbackSerializerFactory);
    }

    @Override
    public ProcessEngineConfigurationImpl setVariableTypes(VariableSerializers variableSerializers) {
        return super.setVariableTypes(variableSerializers);
    }

    @Override
    public ExpressionManager getExpressionManager() {
        return super.getExpressionManager();
    }

    @Override
    public ProcessEngineConfigurationImpl setExpressionManager(ExpressionManager expressionManager) {
        return super.setExpressionManager(expressionManager);
    }

    @Override
    public BusinessCalendarManager getBusinessCalendarManager() {
        return super.getBusinessCalendarManager();
    }

    @Override
    public ProcessEngineConfigurationImpl setBusinessCalendarManager(BusinessCalendarManager businessCalendarManager) {
        return super.setBusinessCalendarManager(businessCalendarManager);
    }

    @Override
    public CommandContextFactory getCommandContextFactory() {
        return super.getCommandContextFactory();
    }

    @Override
    public ProcessEngineConfigurationImpl setCommandContextFactory(CommandContextFactory commandContextFactory) {
        return super.setCommandContextFactory(commandContextFactory);
    }

    @Override
    public TransactionContextFactory getTransactionContextFactory() {
        return super.getTransactionContextFactory();
    }

    @Override
    public ProcessEngineConfigurationImpl setTransactionContextFactory(TransactionContextFactory transactionContextFactory) {
        return super.setTransactionContextFactory(transactionContextFactory);
    }

    @Override
    public BpmnParseFactory getBpmnParseFactory() {
        return super.getBpmnParseFactory();
    }

    @Override
    public ProcessEngineConfigurationImpl setBpmnParseFactory(BpmnParseFactory bpmnParseFactory) {
        return super.setBpmnParseFactory(bpmnParseFactory);
    }

    @Override
    public List<Deployer> getCustomPreDeployers() {
        return super.getCustomPreDeployers();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomPreDeployers(List<Deployer> customPreDeployers) {
        return super.setCustomPreDeployers(customPreDeployers);
    }

    @Override
    public List<Deployer> getCustomPostDeployers() {
        return super.getCustomPostDeployers();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomPostDeployers(List<Deployer> customPostDeployers) {
        return super.setCustomPostDeployers(customPostDeployers);
    }

    @Override
    public void setCacheFactory(CacheFactory cacheFactory) {
        super.setCacheFactory(cacheFactory);
    }

    @Override
    public void setCacheCapacity(int cacheCapacity) {
        super.setCacheCapacity(cacheCapacity);
    }

    @Override
    public void setEnableFetchProcessDefinitionDescription(boolean enableFetchProcessDefinitionDescription) {
        super.setEnableFetchProcessDefinitionDescription(enableFetchProcessDefinitionDescription);
    }

    @Override
    public boolean getEnableFetchProcessDefinitionDescription() {
        return super.getEnableFetchProcessDefinitionDescription();
    }

    @Override
    public Permission getDefaultUserPermissionForTask() {
        return super.getDefaultUserPermissionForTask();
    }

    @Override
    public ProcessEngineConfigurationImpl setDefaultUserPermissionForTask(Permission defaultUserPermissionForTask) {
        return super.setDefaultUserPermissionForTask(defaultUserPermissionForTask);
    }

    @Override
    public ProcessEngineConfigurationImpl setEnableHistoricInstancePermissions(boolean enable) {
        return super.setEnableHistoricInstancePermissions(enable);
    }

    @Override
    public boolean isEnableHistoricInstancePermissions() {
        return super.isEnableHistoricInstancePermissions();
    }

    @Override
    public Map<String, JobHandler> getJobHandlers() {
        return super.getJobHandlers();
    }

    @Override
    public ProcessEngineConfigurationImpl setJobHandlers(Map<String, JobHandler> jobHandlers) {
        return super.setJobHandlers(jobHandlers);
    }

    @Override
    public SqlSessionFactory getSqlSessionFactory() {
        return super.getSqlSessionFactory();
    }

    @Override
    public ProcessEngineConfigurationImpl setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        return super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public DbSqlSessionFactory getDbSqlSessionFactory() {
        return super.getDbSqlSessionFactory();
    }

    @Override
    public ProcessEngineConfigurationImpl setDbSqlSessionFactory(DbSqlSessionFactory dbSqlSessionFactory) {
        return super.setDbSqlSessionFactory(dbSqlSessionFactory);
    }

    @Override
    public TransactionFactory getTransactionFactory() {
        return super.getTransactionFactory();
    }

    @Override
    public ProcessEngineConfigurationImpl setTransactionFactory(TransactionFactory transactionFactory) {
        return super.setTransactionFactory(transactionFactory);
    }

    @Override
    public List<SessionFactory> getCustomSessionFactories() {
        return super.getCustomSessionFactories();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomSessionFactories(List<SessionFactory> customSessionFactories) {
        return super.setCustomSessionFactories(customSessionFactories);
    }

    @Override
    public List<JobHandler> getCustomJobHandlers() {
        return super.getCustomJobHandlers();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomJobHandlers(List<JobHandler> customJobHandlers) {
        return super.setCustomJobHandlers(customJobHandlers);
    }

    @Override
    public List<FormEngine> getCustomFormEngines() {
        return super.getCustomFormEngines();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomFormEngines(List<FormEngine> customFormEngines) {
        return super.setCustomFormEngines(customFormEngines);
    }

    @Override
    public List<AbstractFormFieldType> getCustomFormTypes() {
        return super.getCustomFormTypes();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomFormTypes(List<AbstractFormFieldType> customFormTypes) {
        return super.setCustomFormTypes(customFormTypes);
    }

    @Override
    public List<TypedValueSerializer> getCustomPreVariableSerializers() {
        return super.getCustomPreVariableSerializers();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomPreVariableSerializers(List<TypedValueSerializer> customPreVariableTypes) {
        return super.setCustomPreVariableSerializers(customPreVariableTypes);
    }

    @Override
    public List<TypedValueSerializer> getCustomPostVariableSerializers() {
        return super.getCustomPostVariableSerializers();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomPostVariableSerializers(List<TypedValueSerializer> customPostVariableTypes) {
        return super.setCustomPostVariableSerializers(customPostVariableTypes);
    }

    @Override
    public List<BpmnParseListener> getCustomPreBPMNParseListeners() {
        return super.getCustomPreBPMNParseListeners();
    }

    @Override
    public void setCustomPreBPMNParseListeners(List<BpmnParseListener> preParseListeners) {
        super.setCustomPreBPMNParseListeners(preParseListeners);
    }

    @Override
    public List<BpmnParseListener> getCustomPostBPMNParseListeners() {
        return super.getCustomPostBPMNParseListeners();
    }

    @Override
    public void setCustomPostBPMNParseListeners(List<BpmnParseListener> postParseListeners) {
        super.setCustomPostBPMNParseListeners(postParseListeners);
    }

    /**
     * @deprecated use {@link #getCustomPreBPMNParseListeners} instead.
     */
    @Override
    public List<BpmnParseListener> getPreParseListeners() {
        return super.getPreParseListeners();
    }

    /**
     * @param preParseListeners
     * @deprecated use {@link #setCustomPreBPMNParseListeners} instead.
     */
    @Override
    public void setPreParseListeners(List<BpmnParseListener> preParseListeners) {
        super.setPreParseListeners(preParseListeners);
    }

    /**
     * @deprecated use {@link #getCustomPostBPMNParseListeners} instead.
     */
    @Override
    public List<BpmnParseListener> getPostParseListeners() {
        return super.getPostParseListeners();
    }

    /**
     * @param postParseListeners
     * @deprecated use {@link #setCustomPostBPMNParseListeners} instead.
     */
    @Override
    public void setPostParseListeners(List<BpmnParseListener> postParseListeners) {
        super.setPostParseListeners(postParseListeners);
    }

    @Override
    public List<CmmnTransformListener> getCustomPreCmmnTransformListeners() {
        return super.getCustomPreCmmnTransformListeners();
    }

    @Override
    public void setCustomPreCmmnTransformListeners(List<CmmnTransformListener> customPreCmmnTransformListeners) {
        super.setCustomPreCmmnTransformListeners(customPreCmmnTransformListeners);
    }

    @Override
    public List<CmmnTransformListener> getCustomPostCmmnTransformListeners() {
        return super.getCustomPostCmmnTransformListeners();
    }

    @Override
    public void setCustomPostCmmnTransformListeners(List<CmmnTransformListener> customPostCmmnTransformListeners) {
        super.setCustomPostCmmnTransformListeners(customPostCmmnTransformListeners);
    }

    @Override
    public Map<Object, Object> getBeans() {
        return super.getBeans();
    }

    @Override
    public void setBeans(Map<Object, Object> beans) {
        super.setBeans(beans);
    }

    @Override
    public ProcessEngineConfigurationImpl setClassLoader(ClassLoader classLoader) {
        return super.setClassLoader(classLoader);
    }

    @Override
    public ProcessEngineConfigurationImpl setDatabaseType(String databaseType) {
        return super.setDatabaseType(databaseType);
    }

    @Override
    public ProcessEngineConfigurationImpl setDatabaseSchemaUpdate(String databaseSchemaUpdate) {
        return super.setDatabaseSchemaUpdate(databaseSchemaUpdate);
    }

    @Override
    public ProcessEngineConfigurationImpl setHistory(String history) {
        return super.setHistory(history);
    }

    @Override
    public ProcessEngineConfigurationImpl setIdBlockSize(int idBlockSize) {
        return super.setIdBlockSize(idBlockSize);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcDriver(String jdbcDriver) {
        return super.setJdbcDriver(jdbcDriver);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcPassword(String jdbcPassword) {
        return super.setJdbcPassword(jdbcPassword);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcUrl(String jdbcUrl) {
        return super.setJdbcUrl(jdbcUrl);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcUsername(String jdbcUsername) {
        return super.setJdbcUsername(jdbcUsername);
    }

    @Override
    public ProcessEngineConfigurationImpl setJobExecutorActivate(boolean jobExecutorActivate) {
        return super.setJobExecutorActivate(jobExecutorActivate);
    }

    @Override
    public ProcessEngineConfigurationImpl setMailServerDefaultFrom(String mailServerDefaultFrom) {
        return super.setMailServerDefaultFrom(mailServerDefaultFrom);
    }

    @Override
    public ProcessEngineConfigurationImpl setMailServerHost(String mailServerHost) {
        return super.setMailServerHost(mailServerHost);
    }

    @Override
    public ProcessEngineConfigurationImpl setMailServerPassword(String mailServerPassword) {
        return super.setMailServerPassword(mailServerPassword);
    }

    @Override
    public ProcessEngineConfigurationImpl setMailServerPort(int mailServerPort) {
        return super.setMailServerPort(mailServerPort);
    }

    @Override
    public ProcessEngineConfigurationImpl setMailServerUseTLS(boolean useTLS) {
        return super.setMailServerUseTLS(useTLS);
    }

    @Override
    public ProcessEngineConfigurationImpl setMailServerUsername(String mailServerUsername) {
        return super.setMailServerUsername(mailServerUsername);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcMaxActiveConnections(int jdbcMaxActiveConnections) {
        return super.setJdbcMaxActiveConnections(jdbcMaxActiveConnections);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcMaxCheckoutTime(int jdbcMaxCheckoutTime) {
        return super.setJdbcMaxCheckoutTime(jdbcMaxCheckoutTime);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcMaxIdleConnections(int jdbcMaxIdleConnections) {
        return super.setJdbcMaxIdleConnections(jdbcMaxIdleConnections);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcMaxWaitTime(int jdbcMaxWaitTime) {
        return super.setJdbcMaxWaitTime(jdbcMaxWaitTime);
    }

    @Override
    public ProcessEngineConfigurationImpl setTransactionsExternallyManaged(boolean transactionsExternallyManaged) {
        return super.setTransactionsExternallyManaged(transactionsExternallyManaged);
    }

    @Override
    public ProcessEngineConfigurationImpl setJpaEntityManagerFactory(Object jpaEntityManagerFactory) {
        return super.setJpaEntityManagerFactory(jpaEntityManagerFactory);
    }

    @Override
    public ProcessEngineConfigurationImpl setJpaHandleTransaction(boolean jpaHandleTransaction) {
        return super.setJpaHandleTransaction(jpaHandleTransaction);
    }

    @Override
    public ProcessEngineConfigurationImpl setJpaCloseEntityManager(boolean jpaCloseEntityManager) {
        return super.setJpaCloseEntityManager(jpaCloseEntityManager);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcPingEnabled(boolean jdbcPingEnabled) {
        return super.setJdbcPingEnabled(jdbcPingEnabled);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcPingQuery(String jdbcPingQuery) {
        return super.setJdbcPingQuery(jdbcPingQuery);
    }

    @Override
    public ProcessEngineConfigurationImpl setJdbcPingConnectionNotUsedFor(int jdbcPingNotUsedFor) {
        return super.setJdbcPingConnectionNotUsedFor(jdbcPingNotUsedFor);
    }

    @Override
    public boolean isDbIdentityUsed() {
        return super.isDbIdentityUsed();
    }

    @Override
    public void setDbIdentityUsed(boolean isDbIdentityUsed) {
        super.setDbIdentityUsed(isDbIdentityUsed);
    }

    @Override
    public boolean isDbHistoryUsed() {
        return super.isDbHistoryUsed();
    }

    @Override
    public void setDbHistoryUsed(boolean isDbHistoryUsed) {
        super.setDbHistoryUsed(isDbHistoryUsed);
    }

    @Override
    public List<ResolverFactory> getResolverFactories() {
        return super.getResolverFactories();
    }

    @Override
    public void setResolverFactories(List<ResolverFactory> resolverFactories) {
        super.setResolverFactories(resolverFactories);
    }

    @Override
    public DeploymentCache getDeploymentCache() {
        return super.getDeploymentCache();
    }

    @Override
    public void setDeploymentCache(DeploymentCache deploymentCache) {
        super.setDeploymentCache(deploymentCache);
    }

    @Override
    public DeploymentHandlerFactory getDeploymentHandlerFactory() {
        return super.getDeploymentHandlerFactory();
    }

    @Override
    public ProcessEngineConfigurationImpl setDeploymentHandlerFactory(DeploymentHandlerFactory deploymentHandlerFactory) {
        return super.setDeploymentHandlerFactory(deploymentHandlerFactory);
    }

    @Override
    public ProcessEngineConfigurationImpl setDelegateInterceptor(DelegateInterceptor delegateInterceptor) {
        return super.setDelegateInterceptor(delegateInterceptor);
    }

    @Override
    public DelegateInterceptor getDelegateInterceptor() {
        return super.getDelegateInterceptor();
    }

    @Override
    public RejectedJobsHandler getCustomRejectedJobsHandler() {
        return super.getCustomRejectedJobsHandler();
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomRejectedJobsHandler(RejectedJobsHandler customRejectedJobsHandler) {
        return super.setCustomRejectedJobsHandler(customRejectedJobsHandler);
    }

    @Override
    public EventHandler getEventHandler(String eventType) {
        return super.getEventHandler(eventType);
    }

    @Override
    public void setEventHandlers(Map<String, EventHandler> eventHandlers) {
        super.setEventHandlers(eventHandlers);
    }

    @Override
    public Map<String, EventHandler> getEventHandlers() {
        return super.getEventHandlers();
    }

    @Override
    public List<EventHandler> getCustomEventHandlers() {
        return super.getCustomEventHandlers();
    }

    @Override
    public void setCustomEventHandlers(List<EventHandler> customEventHandlers) {
        super.setCustomEventHandlers(customEventHandlers);
    }

    @Override
    public FailedJobCommandFactory getFailedJobCommandFactory() {
        return super.getFailedJobCommandFactory();
    }

    @Override
    public ProcessEngineConfigurationImpl setFailedJobCommandFactory(FailedJobCommandFactory failedJobCommandFactory) {
        return super.setFailedJobCommandFactory(failedJobCommandFactory);
    }

    /**
     * Allows configuring a database table prefix which is used for all runtime operations of the process engine.
     * For example, if you specify a prefix named 'PRE1.', activiti will query for executions in a table named
     * 'PRE1.ACT_RU_EXECUTION_'.
     * <p>
     * <p/>
     * <strong>NOTE: the prefix is not respected by automatic database schema management. If you use
     * {@link ProcessEngineConfiguration#DB_SCHEMA_UPDATE_CREATE_DROP}
     * or {@link ProcessEngineConfiguration#DB_SCHEMA_UPDATE_TRUE}, activiti will create the database tables
     * using the default names, regardless of the prefix configured here.</strong>
     *
     * @param databaseTablePrefix
     * @since 5.9
     */
    @Override
    public ProcessEngineConfiguration setDatabaseTablePrefix(String databaseTablePrefix) {
        return super.setDatabaseTablePrefix(databaseTablePrefix);
    }

    @Override
    public String getDatabaseTablePrefix() {
        return super.getDatabaseTablePrefix();
    }

    @Override
    public boolean isCreateDiagramOnDeploy() {
        return super.isCreateDiagramOnDeploy();
    }

    @Override
    public ProcessEngineConfiguration setCreateDiagramOnDeploy(boolean createDiagramOnDeploy) {
        return super.setCreateDiagramOnDeploy(createDiagramOnDeploy);
    }

    @Override
    public String getDatabaseSchema() {
        return super.getDatabaseSchema();
    }

    @Override
    public void setDatabaseSchema(String databaseSchema) {
        super.setDatabaseSchema(databaseSchema);
    }

    @Override
    public DataSource getIdGeneratorDataSource() {
        return super.getIdGeneratorDataSource();
    }

    @Override
    public void setIdGeneratorDataSource(DataSource idGeneratorDataSource) {
        super.setIdGeneratorDataSource(idGeneratorDataSource);
    }

    @Override
    public String getIdGeneratorDataSourceJndiName() {
        return super.getIdGeneratorDataSourceJndiName();
    }

    @Override
    public void setIdGeneratorDataSourceJndiName(String idGeneratorDataSourceJndiName) {
        super.setIdGeneratorDataSourceJndiName(idGeneratorDataSourceJndiName);
    }

    @Override
    public ProcessApplicationManager getProcessApplicationManager() {
        return super.getProcessApplicationManager();
    }

    @Override
    public void setProcessApplicationManager(ProcessApplicationManager processApplicationManager) {
        super.setProcessApplicationManager(processApplicationManager);
    }

    @Override
    public CommandExecutor getCommandExecutorSchemaOperations() {
        return super.getCommandExecutorSchemaOperations();
    }

    @Override
    public void setCommandExecutorSchemaOperations(CommandExecutor commandExecutorSchemaOperations) {
        super.setCommandExecutorSchemaOperations(commandExecutorSchemaOperations);
    }

    @Override
    public CorrelationHandler getCorrelationHandler() {
        return super.getCorrelationHandler();
    }

    @Override
    public void setCorrelationHandler(CorrelationHandler correlationHandler) {
        super.setCorrelationHandler(correlationHandler);
    }

    @Override
    public ConditionHandler getConditionHandler() {
        return super.getConditionHandler();
    }

    @Override
    public void setConditionHandler(ConditionHandler conditionHandler) {
        super.setConditionHandler(conditionHandler);
    }

    @Override
    public ProcessEngineConfigurationImpl setHistoryEventHandler(HistoryEventHandler historyEventHandler) {
        return super.setHistoryEventHandler(historyEventHandler);
    }

    @Override
    public HistoryEventHandler getHistoryEventHandler() {
        return super.getHistoryEventHandler();
    }

    @Override
    public boolean isEnableDefaultDbHistoryEventHandler() {
        return super.isEnableDefaultDbHistoryEventHandler();
    }

    @Override
    public void setEnableDefaultDbHistoryEventHandler(boolean enableDefaultDbHistoryEventHandler) {
        super.setEnableDefaultDbHistoryEventHandler(enableDefaultDbHistoryEventHandler);
    }

    @Override
    public List<HistoryEventHandler> getCustomHistoryEventHandlers() {
        return super.getCustomHistoryEventHandlers();
    }

    @Override
    public void setCustomHistoryEventHandlers(List<HistoryEventHandler> customHistoryEventHandlers) {
        super.setCustomHistoryEventHandlers(customHistoryEventHandlers);
    }

    @Override
    public IncidentHandler getIncidentHandler(String incidentType) {
        return super.getIncidentHandler(incidentType);
    }

    @Override
    public Map<String, IncidentHandler> getIncidentHandlers() {
        return super.getIncidentHandlers();
    }

    @Override
    public void setIncidentHandlers(Map<String, IncidentHandler> incidentHandlers) {
        super.setIncidentHandlers(incidentHandlers);
    }

    @Override
    public List<IncidentHandler> getCustomIncidentHandlers() {
        return super.getCustomIncidentHandlers();
    }

    @Override
    public void setCustomIncidentHandlers(List<IncidentHandler> customIncidentHandlers) {
        super.setCustomIncidentHandlers(customIncidentHandlers);
    }

    @Override
    public Map<String, BatchJobHandler<?>> getBatchHandlers() {
        return super.getBatchHandlers();
    }

    @Override
    public void setBatchHandlers(Map<String, BatchJobHandler<?>> batchHandlers) {
        super.setBatchHandlers(batchHandlers);
    }

    @Override
    public List<BatchJobHandler<?>> getCustomBatchJobHandlers() {
        return super.getCustomBatchJobHandlers();
    }

    @Override
    public void setCustomBatchJobHandlers(List<BatchJobHandler<?>> customBatchJobHandlers) {
        super.setCustomBatchJobHandlers(customBatchJobHandlers);
    }

    @Override
    public int getBatchJobsPerSeed() {
        return super.getBatchJobsPerSeed();
    }

    @Override
    public void setBatchJobsPerSeed(int batchJobsPerSeed) {
        super.setBatchJobsPerSeed(batchJobsPerSeed);
    }

    @Override
    public Map<String, Integer> getInvocationsPerBatchJobByBatchType() {
        return super.getInvocationsPerBatchJobByBatchType();
    }

    @Override
    public ProcessEngineConfigurationImpl setInvocationsPerBatchJobByBatchType(Map<String, Integer> invocationsPerBatchJobByBatchType) {
        return super.setInvocationsPerBatchJobByBatchType(invocationsPerBatchJobByBatchType);
    }

    @Override
    public int getInvocationsPerBatchJob() {
        return super.getInvocationsPerBatchJob();
    }

    @Override
    public void setInvocationsPerBatchJob(int invocationsPerBatchJob) {
        super.setInvocationsPerBatchJob(invocationsPerBatchJob);
    }

    @Override
    public int getBatchPollTime() {
        return super.getBatchPollTime();
    }

    @Override
    public void setBatchPollTime(int batchPollTime) {
        super.setBatchPollTime(batchPollTime);
    }

    @Override
    public long getBatchJobPriority() {
        return super.getBatchJobPriority();
    }

    @Override
    public void setBatchJobPriority(long batchJobPriority) {
        super.setBatchJobPriority(batchJobPriority);
    }

    @Override
    public SessionFactory getIdentityProviderSessionFactory() {
        return super.getIdentityProviderSessionFactory();
    }

    @Override
    public void setIdentityProviderSessionFactory(SessionFactory identityProviderSessionFactory) {
        super.setIdentityProviderSessionFactory(identityProviderSessionFactory);
    }

    @Override
    public SaltGenerator getSaltGenerator() {
        return super.getSaltGenerator();
    }

    @Override
    public void setSaltGenerator(SaltGenerator saltGenerator) {
        super.setSaltGenerator(saltGenerator);
    }

    @Override
    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        super.setPasswordEncryptor(passwordEncryptor);
    }

    @Override
    public PasswordEncryptor getPasswordEncryptor() {
        return super.getPasswordEncryptor();
    }

    @Override
    public List<PasswordEncryptor> getCustomPasswordChecker() {
        return super.getCustomPasswordChecker();
    }

    @Override
    public void setCustomPasswordChecker(List<PasswordEncryptor> customPasswordChecker) {
        super.setCustomPasswordChecker(customPasswordChecker);
    }

    @Override
    public PasswordManager getPasswordManager() {
        return super.getPasswordManager();
    }

    @Override
    public void setPasswordManager(PasswordManager passwordManager) {
        super.setPasswordManager(passwordManager);
    }

    @Override
    public Set<String> getRegisteredDeployments() {
        return super.getRegisteredDeployments();
    }

    @Override
    public void setRegisteredDeployments(Set<String> registeredDeployments) {
        super.setRegisteredDeployments(registeredDeployments);
    }

    @Override
    public ResourceAuthorizationProvider getResourceAuthorizationProvider() {
        return super.getResourceAuthorizationProvider();
    }

    @Override
    public void setResourceAuthorizationProvider(ResourceAuthorizationProvider resourceAuthorizationProvider) {
        super.setResourceAuthorizationProvider(resourceAuthorizationProvider);
    }

    @Override
    public PermissionProvider getPermissionProvider() {
        return super.getPermissionProvider();
    }

    @Override
    public void setPermissionProvider(PermissionProvider permissionProvider) {
        super.setPermissionProvider(permissionProvider);
    }

    @Override
    public List<ProcessEnginePlugin> getProcessEnginePlugins() {
        return super.getProcessEnginePlugins();
    }

    @Override
    public void setProcessEnginePlugins(List<ProcessEnginePlugin> processEnginePlugins) {
        super.setProcessEnginePlugins(processEnginePlugins);
    }

    @Override
    public ProcessEngineConfigurationImpl setHistoryEventProducer(HistoryEventProducer historyEventProducer) {
        return super.setHistoryEventProducer(historyEventProducer);
    }

    @Override
    public HistoryEventProducer getHistoryEventProducer() {
        return super.getHistoryEventProducer();
    }

    @Override
    public ProcessEngineConfigurationImpl setCmmnHistoryEventProducer(CmmnHistoryEventProducer cmmnHistoryEventProducer) {
        return super.setCmmnHistoryEventProducer(cmmnHistoryEventProducer);
    }

    @Override
    public CmmnHistoryEventProducer getCmmnHistoryEventProducer() {
        return super.getCmmnHistoryEventProducer();
    }

    @Override
    public ProcessEngineConfigurationImpl setDmnHistoryEventProducer(DmnHistoryEventProducer dmnHistoryEventProducer) {
        return super.setDmnHistoryEventProducer(dmnHistoryEventProducer);
    }

    @Override
    public DmnHistoryEventProducer getDmnHistoryEventProducer() {
        return super.getDmnHistoryEventProducer();
    }

    @Override
    public Map<String, Class<? extends FormFieldValidator>> getCustomFormFieldValidators() {
        return super.getCustomFormFieldValidators();
    }

    @Override
    public void setCustomFormFieldValidators(Map<String, Class<? extends FormFieldValidator>> customFormFieldValidators) {
        super.setCustomFormFieldValidators(customFormFieldValidators);
    }

    @Override
    public void setFormValidators(FormValidators formValidators) {
        super.setFormValidators(formValidators);
    }

    @Override
    public FormValidators getFormValidators() {
        return super.getFormValidators();
    }

    @Override
    public boolean isExecutionTreePrefetchEnabled() {
        return super.isExecutionTreePrefetchEnabled();
    }

    @Override
    public void setExecutionTreePrefetchEnabled(boolean isExecutionTreePrefetchingEnabled) {
        super.setExecutionTreePrefetchEnabled(isExecutionTreePrefetchingEnabled);
    }

    @Override
    public ProcessEngineImpl getProcessEngine() {
        return super.getProcessEngine();
    }

    /**
     * If set to true, the process engine will save all script variables (created from Java Script, Groovy ...)
     * as process variables.
     *
     * @param autoStoreScriptVariables
     */
    @Override
    public void setAutoStoreScriptVariables(boolean autoStoreScriptVariables) {
        super.setAutoStoreScriptVariables(autoStoreScriptVariables);
    }

    /**
     * @return true if the process engine should save all script variables (created from Java Script, Groovy ...)
     * as process variables.
     */
    @Override
    public boolean isAutoStoreScriptVariables() {
        return super.isAutoStoreScriptVariables();
    }

    /**
     * If set to true, the process engine will attempt to pre-compile script sources at runtime
     * to optimize script task execution performance.
     *
     * @param enableScriptCompilation
     */
    @Override
    public void setEnableScriptCompilation(boolean enableScriptCompilation) {
        super.setEnableScriptCompilation(enableScriptCompilation);
    }

    /**
     * @return true if compilation of script sources ins enabled. False otherwise.
     */
    @Override
    public boolean isEnableScriptCompilation() {
        return super.isEnableScriptCompilation();
    }

    @Override
    public boolean isEnableGracefulDegradationOnContextSwitchFailure() {
        return super.isEnableGracefulDegradationOnContextSwitchFailure();
    }

    /**
     * <p>If set to true, the process engine will tolerate certain exceptions that may result
     * from the fact that it cannot switch to the context of a process application that has made
     * a deployment.</p>
     * <p>
     * <p>Affects the following scenarios:</p>
     * <ul>
     * <li><b>Determining job priorities</b>: uses a default priority in case an expression fails to evaluate</li>
     * </ul>
     *
     * @param enableGracefulDegradationOnContextSwitchFailure
     */
    @Override
    public void setEnableGracefulDegradationOnContextSwitchFailure(boolean enableGracefulDegradationOnContextSwitchFailure) {
        super.setEnableGracefulDegradationOnContextSwitchFailure(enableGracefulDegradationOnContextSwitchFailure);
    }

    /**
     * @return true if the process engine acquires an exclusive lock when creating a deployment.
     */
    @Override
    public boolean isDeploymentLockUsed() {
        return super.isDeploymentLockUsed();
    }

    /**
     * If set to true, the process engine will acquire an exclusive lock when creating a deployment.
     * This ensures that {@link DeploymentBuilder#enableDuplicateFiltering()} works correctly in a clustered environment.
     *
     * @param isDeploymentLockUsed
     */
    @Override
    public void setDeploymentLockUsed(boolean isDeploymentLockUsed) {
        super.setDeploymentLockUsed(isDeploymentLockUsed);
    }

    /**
     * @return true if deployment processing must be synchronized
     */
    @Override
    public boolean isDeploymentSynchronized() {
        return super.isDeploymentSynchronized();
    }

    /**
     * Sets if deployment processing must be synchronized.
     *
     * @param deploymentSynchronized {@code true} when deployment must be synchronized,
     *                               {@code false} when several depoloyments may be processed in parallel
     */
    @Override
    public void setDeploymentSynchronized(boolean deploymentSynchronized) {
        super.setDeploymentSynchronized(deploymentSynchronized);
    }

    @Override
    public boolean isCmmnEnabled() {
        return super.isCmmnEnabled();
    }

    @Override
    public void setCmmnEnabled(boolean cmmnEnabled) {
        super.setCmmnEnabled(cmmnEnabled);
    }

    @Override
    public boolean isDmnEnabled() {
        return super.isDmnEnabled();
    }

    @Override
    public void setDmnEnabled(boolean dmnEnabled) {
        super.setDmnEnabled(dmnEnabled);
    }

    @Override
    public boolean isStandaloneTasksEnabled() {
        return super.isStandaloneTasksEnabled();
    }

    @Override
    public ProcessEngineConfigurationImpl setStandaloneTasksEnabled(boolean standaloneTasksEnabled) {
        return super.setStandaloneTasksEnabled(standaloneTasksEnabled);
    }

    @Override
    public ScriptFactory getScriptFactory() {
        return super.getScriptFactory();
    }

    @Override
    public ScriptingEnvironment getScriptingEnvironment() {
        return super.getScriptingEnvironment();
    }

    @Override
    public void setScriptFactory(ScriptFactory scriptFactory) {
        super.setScriptFactory(scriptFactory);
    }

    @Override
    public void setScriptingEnvironment(ScriptingEnvironment scriptingEnvironment) {
        super.setScriptingEnvironment(scriptingEnvironment);
    }

    @Override
    public List<ScriptEnvResolver> getEnvScriptResolvers() {
        return super.getEnvScriptResolvers();
    }

    @Override
    public void setEnvScriptResolvers(List<ScriptEnvResolver> scriptEnvResolvers) {
        super.setEnvScriptResolvers(scriptEnvResolvers);
    }

    @Override
    public ProcessEngineConfiguration setArtifactFactory(ArtifactFactory artifactFactory) {
        return super.setArtifactFactory(artifactFactory);
    }

    @Override
    public ArtifactFactory getArtifactFactory() {
        return super.getArtifactFactory();
    }

    @Override
    public String getDefaultSerializationFormat() {
        return super.getDefaultSerializationFormat();
    }

    @Override
    public ProcessEngineConfigurationImpl setDefaultSerializationFormat(String defaultSerializationFormat) {
        return super.setDefaultSerializationFormat(defaultSerializationFormat);
    }

    @Override
    public boolean isJavaSerializationFormatEnabled() {
        return super.isJavaSerializationFormatEnabled();
    }

    @Override
    public void setJavaSerializationFormatEnabled(boolean javaSerializationFormatEnabled) {
        super.setJavaSerializationFormatEnabled(javaSerializationFormatEnabled);
    }

    @Override
    public ProcessEngineConfigurationImpl setDefaultCharsetName(String defaultCharsetName) {
        return super.setDefaultCharsetName(defaultCharsetName);
    }

    @Override
    public ProcessEngineConfigurationImpl setDefaultCharset(Charset defautlCharset) {
        return super.setDefaultCharset(defautlCharset);
    }

    @Override
    public Charset getDefaultCharset() {
        return super.getDefaultCharset();
    }

    @Override
    public boolean isDbEntityCacheReuseEnabled() {
        return super.isDbEntityCacheReuseEnabled();
    }

    @Override
    public ProcessEngineConfigurationImpl setDbEntityCacheReuseEnabled(boolean isDbEntityCacheReuseEnabled) {
        return super.setDbEntityCacheReuseEnabled(isDbEntityCacheReuseEnabled);
    }

    @Override
    public DbEntityCacheKeyMapping getDbEntityCacheKeyMapping() {
        return super.getDbEntityCacheKeyMapping();
    }

    @Override
    public ProcessEngineConfigurationImpl setDbEntityCacheKeyMapping(DbEntityCacheKeyMapping dbEntityCacheKeyMapping) {
        return super.setDbEntityCacheKeyMapping(dbEntityCacheKeyMapping);
    }

    @Override
    public ProcessEngineConfigurationImpl setCustomHistoryLevels(List<HistoryLevel> customHistoryLevels) {
        return super.setCustomHistoryLevels(customHistoryLevels);
    }

    @Override
    public List<HistoryLevel> getHistoryLevels() {
        return super.getHistoryLevels();
    }

    @Override
    public List<HistoryLevel> getCustomHistoryLevels() {
        return super.getCustomHistoryLevels();
    }

    @Override
    public boolean isInvokeCustomVariableListeners() {
        return super.isInvokeCustomVariableListeners();
    }

    @Override
    public ProcessEngineConfigurationImpl setInvokeCustomVariableListeners(boolean isInvokeCustomVariableListeners) {
        return super.setInvokeCustomVariableListeners(isInvokeCustomVariableListeners);
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public MetricsRegistry getMetricsRegistry() {
        return super.getMetricsRegistry();
    }

    @Override
    public ProcessEngineConfigurationImpl setMetricsRegistry(MetricsRegistry metricsRegistry) {
        return super.setMetricsRegistry(metricsRegistry);
    }

    @Override
    public ProcessEngineConfigurationImpl setMetricsEnabled(boolean isMetricsEnabled) {
        return super.setMetricsEnabled(isMetricsEnabled);
    }

    @Override
    public boolean isMetricsEnabled() {
        return super.isMetricsEnabled();
    }

    @Override
    public DbMetricsReporter getDbMetricsReporter() {
        return super.getDbMetricsReporter();
    }

    @Override
    public ProcessEngineConfigurationImpl setDbMetricsReporter(DbMetricsReporter dbMetricsReporter) {
        return super.setDbMetricsReporter(dbMetricsReporter);
    }

    @Override
    public boolean isDbMetricsReporterActivate() {
        return super.isDbMetricsReporterActivate();
    }

    @Override
    public ProcessEngineConfigurationImpl setDbMetricsReporterActivate(boolean isDbMetricsReporterEnabled) {
        return super.setDbMetricsReporterActivate(isDbMetricsReporterEnabled);
    }

    /**
     * @deprecated use {@link #getHostnameProvider()} instead.
     */
    @Override
    public MetricsReporterIdProvider getMetricsReporterIdProvider() {
        return super.getMetricsReporterIdProvider();
    }

    /**
     * @param metricsReporterIdProvider
     * @deprecated use {@link #setHostnameProvider(HostnameProvider)} instead.
     */
    @Override
    public ProcessEngineConfigurationImpl setMetricsReporterIdProvider(MetricsReporterIdProvider metricsReporterIdProvider) {
        return super.setMetricsReporterIdProvider(metricsReporterIdProvider);
    }

    @Override
    public String getHostname() {
        return super.getHostname();
    }

    @Override
    public ProcessEngineConfigurationImpl setHostname(String hostname) {
        return super.setHostname(hostname);
    }

    @Override
    public HostnameProvider getHostnameProvider() {
        return super.getHostnameProvider();
    }

    @Override
    public ProcessEngineConfigurationImpl setHostnameProvider(HostnameProvider hostnameProvider) {
        return super.setHostnameProvider(hostnameProvider);
    }

    @Override
    public boolean isEnableScriptEngineCaching() {
        return super.isEnableScriptEngineCaching();
    }

    @Override
    public ProcessEngineConfigurationImpl setEnableScriptEngineCaching(boolean enableScriptEngineCaching) {
        return super.setEnableScriptEngineCaching(enableScriptEngineCaching);
    }

    @Override
    public boolean isEnableFetchScriptEngineFromProcessApplication() {
        return super.isEnableFetchScriptEngineFromProcessApplication();
    }

    @Override
    public ProcessEngineConfigurationImpl setEnableFetchScriptEngineFromProcessApplication(boolean enable) {
        return super.setEnableFetchScriptEngineFromProcessApplication(enable);
    }

    @Override
    public boolean isEnableExpressionsInAdhocQueries() {
        return super.isEnableExpressionsInAdhocQueries();
    }

    @Override
    public void setEnableExpressionsInAdhocQueries(boolean enableExpressionsInAdhocQueries) {
        super.setEnableExpressionsInAdhocQueries(enableExpressionsInAdhocQueries);
    }

    @Override
    public boolean isEnableExpressionsInStoredQueries() {
        return super.isEnableExpressionsInStoredQueries();
    }

    @Override
    public void setEnableExpressionsInStoredQueries(boolean enableExpressionsInStoredQueries) {
        super.setEnableExpressionsInStoredQueries(enableExpressionsInStoredQueries);
    }

    @Override
    public boolean isEnableXxeProcessing() {
        return super.isEnableXxeProcessing();
    }

    @Override
    public void setEnableXxeProcessing(boolean enableXxeProcessing) {
        super.setEnableXxeProcessing(enableXxeProcessing);
    }

    @Override
    public ProcessEngineConfigurationImpl setBpmnStacktraceVerbose(boolean isBpmnStacktraceVerbose) {
        return super.setBpmnStacktraceVerbose(isBpmnStacktraceVerbose);
    }

    @Override
    public boolean isBpmnStacktraceVerbose() {
        return super.isBpmnStacktraceVerbose();
    }

    @Override
    public boolean isForceCloseMybatisConnectionPool() {
        return super.isForceCloseMybatisConnectionPool();
    }

    @Override
    public ProcessEngineConfigurationImpl setForceCloseMybatisConnectionPool(boolean forceCloseMybatisConnectionPool) {
        return super.setForceCloseMybatisConnectionPool(forceCloseMybatisConnectionPool);
    }

    @Override
    public boolean isRestrictUserOperationLogToAuthenticatedUsers() {
        return super.isRestrictUserOperationLogToAuthenticatedUsers();
    }

    @Override
    public ProcessEngineConfigurationImpl setRestrictUserOperationLogToAuthenticatedUsers(boolean restrictUserOperationLogToAuthenticatedUsers) {
        return super.setRestrictUserOperationLogToAuthenticatedUsers(restrictUserOperationLogToAuthenticatedUsers);
    }

    @Override
    public ProcessEngineConfigurationImpl setTenantIdProvider(TenantIdProvider tenantIdProvider) {
        return super.setTenantIdProvider(tenantIdProvider);
    }

    @Override
    public TenantIdProvider getTenantIdProvider() {
        return super.getTenantIdProvider();
    }

    @Override
    public void setMigrationActivityMatcher(MigrationActivityMatcher migrationActivityMatcher) {
        super.setMigrationActivityMatcher(migrationActivityMatcher);
    }

    @Override
    public MigrationActivityMatcher getMigrationActivityMatcher() {
        return super.getMigrationActivityMatcher();
    }

    @Override
    public void setCustomPreMigrationActivityValidators(List<MigrationActivityValidator> customPreMigrationActivityValidators) {
        super.setCustomPreMigrationActivityValidators(customPreMigrationActivityValidators);
    }

    @Override
    public List<MigrationActivityValidator> getCustomPreMigrationActivityValidators() {
        return super.getCustomPreMigrationActivityValidators();
    }

    @Override
    public void setCustomPostMigrationActivityValidators(List<MigrationActivityValidator> customPostMigrationActivityValidators) {
        super.setCustomPostMigrationActivityValidators(customPostMigrationActivityValidators);
    }

    @Override
    public List<MigrationActivityValidator> getCustomPostMigrationActivityValidators() {
        return super.getCustomPostMigrationActivityValidators();
    }

    @Override
    public List<MigrationActivityValidator> getDefaultMigrationActivityValidators() {
        return super.getDefaultMigrationActivityValidators();
    }

    @Override
    public void setMigrationInstructionGenerator(MigrationInstructionGenerator migrationInstructionGenerator) {
        super.setMigrationInstructionGenerator(migrationInstructionGenerator);
    }

    @Override
    public MigrationInstructionGenerator getMigrationInstructionGenerator() {
        return super.getMigrationInstructionGenerator();
    }

    @Override
    public void setMigrationInstructionValidators(List<MigrationInstructionValidator> migrationInstructionValidators) {
        super.setMigrationInstructionValidators(migrationInstructionValidators);
    }

    @Override
    public List<MigrationInstructionValidator> getMigrationInstructionValidators() {
        return super.getMigrationInstructionValidators();
    }

    @Override
    public void setCustomPostMigrationInstructionValidators(List<MigrationInstructionValidator> customPostMigrationInstructionValidators) {
        super.setCustomPostMigrationInstructionValidators(customPostMigrationInstructionValidators);
    }

    @Override
    public List<MigrationInstructionValidator> getCustomPostMigrationInstructionValidators() {
        return super.getCustomPostMigrationInstructionValidators();
    }

    @Override
    public void setCustomPreMigrationInstructionValidators(List<MigrationInstructionValidator> customPreMigrationInstructionValidators) {
        super.setCustomPreMigrationInstructionValidators(customPreMigrationInstructionValidators);
    }

    @Override
    public List<MigrationInstructionValidator> getCustomPreMigrationInstructionValidators() {
        return super.getCustomPreMigrationInstructionValidators();
    }

    @Override
    public List<MigrationInstructionValidator> getDefaultMigrationInstructionValidators() {
        return super.getDefaultMigrationInstructionValidators();
    }

    @Override
    public void setMigratingActivityInstanceValidators(List<MigratingActivityInstanceValidator> migratingActivityInstanceValidators) {
        super.setMigratingActivityInstanceValidators(migratingActivityInstanceValidators);
    }

    @Override
    public List<MigratingActivityInstanceValidator> getMigratingActivityInstanceValidators() {
        return super.getMigratingActivityInstanceValidators();
    }

    @Override
    public void setCustomPostMigratingActivityInstanceValidators(List<MigratingActivityInstanceValidator> customPostMigratingActivityInstanceValidators) {
        super.setCustomPostMigratingActivityInstanceValidators(customPostMigratingActivityInstanceValidators);
    }

    @Override
    public List<MigratingActivityInstanceValidator> getCustomPostMigratingActivityInstanceValidators() {
        return super.getCustomPostMigratingActivityInstanceValidators();
    }

    @Override
    public void setCustomPreMigratingActivityInstanceValidators(List<MigratingActivityInstanceValidator> customPreMigratingActivityInstanceValidators) {
        super.setCustomPreMigratingActivityInstanceValidators(customPreMigratingActivityInstanceValidators);
    }

    @Override
    public List<MigratingActivityInstanceValidator> getCustomPreMigratingActivityInstanceValidators() {
        return super.getCustomPreMigratingActivityInstanceValidators();
    }

    @Override
    public List<MigratingTransitionInstanceValidator> getMigratingTransitionInstanceValidators() {
        return super.getMigratingTransitionInstanceValidators();
    }

    @Override
    public List<MigratingCompensationInstanceValidator> getMigratingCompensationInstanceValidators() {
        return super.getMigratingCompensationInstanceValidators();
    }

    @Override
    public List<MigratingActivityInstanceValidator> getDefaultMigratingActivityInstanceValidators() {
        return super.getDefaultMigratingActivityInstanceValidators();
    }

    @Override
    public List<MigratingTransitionInstanceValidator> getDefaultMigratingTransitionInstanceValidators() {
        return super.getDefaultMigratingTransitionInstanceValidators();
    }

    @Override
    public List<CommandChecker> getCommandCheckers() {
        return super.getCommandCheckers();
    }

    @Override
    public void setCommandCheckers(List<CommandChecker> commandCheckers) {
        super.setCommandCheckers(commandCheckers);
    }

    @Override
    public ProcessEngineConfigurationImpl setUseSharedSqlSessionFactory(boolean isUseSharedSqlSessionFactory) {
        return super.setUseSharedSqlSessionFactory(isUseSharedSqlSessionFactory);
    }

    @Override
    public boolean isUseSharedSqlSessionFactory() {
        return super.isUseSharedSqlSessionFactory();
    }

    @Override
    public boolean getDisableStrictCallActivityValidation() {
        return super.getDisableStrictCallActivityValidation();
    }

    @Override
    public void setDisableStrictCallActivityValidation(boolean disableStrictCallActivityValidation) {
        super.setDisableStrictCallActivityValidation(disableStrictCallActivityValidation);
    }

    @Override
    public String getHistoryCleanupBatchWindowStartTime() {
        return super.getHistoryCleanupBatchWindowStartTime();
    }

    @Override
    public void setHistoryCleanupBatchWindowStartTime(String historyCleanupBatchWindowStartTime) {
        super.setHistoryCleanupBatchWindowStartTime(historyCleanupBatchWindowStartTime);
    }

    @Override
    public String getHistoryCleanupBatchWindowEndTime() {
        return super.getHistoryCleanupBatchWindowEndTime();
    }

    @Override
    public void setHistoryCleanupBatchWindowEndTime(String historyCleanupBatchWindowEndTime) {
        super.setHistoryCleanupBatchWindowEndTime(historyCleanupBatchWindowEndTime);
    }

    @Override
    public String getMondayHistoryCleanupBatchWindowStartTime() {
        return super.getMondayHistoryCleanupBatchWindowStartTime();
    }

    @Override
    public void setMondayHistoryCleanupBatchWindowStartTime(String mondayHistoryCleanupBatchWindowStartTime) {
        super.setMondayHistoryCleanupBatchWindowStartTime(mondayHistoryCleanupBatchWindowStartTime);
    }

    @Override
    public String getMondayHistoryCleanupBatchWindowEndTime() {
        return super.getMondayHistoryCleanupBatchWindowEndTime();
    }

    @Override
    public void setMondayHistoryCleanupBatchWindowEndTime(String mondayHistoryCleanupBatchWindowEndTime) {
        super.setMondayHistoryCleanupBatchWindowEndTime(mondayHistoryCleanupBatchWindowEndTime);
    }

    @Override
    public String getTuesdayHistoryCleanupBatchWindowStartTime() {
        return super.getTuesdayHistoryCleanupBatchWindowStartTime();
    }

    @Override
    public void setTuesdayHistoryCleanupBatchWindowStartTime(String tuesdayHistoryCleanupBatchWindowStartTime) {
        super.setTuesdayHistoryCleanupBatchWindowStartTime(tuesdayHistoryCleanupBatchWindowStartTime);
    }

    @Override
    public String getTuesdayHistoryCleanupBatchWindowEndTime() {
        return super.getTuesdayHistoryCleanupBatchWindowEndTime();
    }

    @Override
    public void setTuesdayHistoryCleanupBatchWindowEndTime(String tuesdayHistoryCleanupBatchWindowEndTime) {
        super.setTuesdayHistoryCleanupBatchWindowEndTime(tuesdayHistoryCleanupBatchWindowEndTime);
    }

    @Override
    public String getWednesdayHistoryCleanupBatchWindowStartTime() {
        return super.getWednesdayHistoryCleanupBatchWindowStartTime();
    }

    @Override
    public void setWednesdayHistoryCleanupBatchWindowStartTime(String wednesdayHistoryCleanupBatchWindowStartTime) {
        super.setWednesdayHistoryCleanupBatchWindowStartTime(wednesdayHistoryCleanupBatchWindowStartTime);
    }

    @Override
    public String getWednesdayHistoryCleanupBatchWindowEndTime() {
        return super.getWednesdayHistoryCleanupBatchWindowEndTime();
    }

    @Override
    public void setWednesdayHistoryCleanupBatchWindowEndTime(String wednesdayHistoryCleanupBatchWindowEndTime) {
        super.setWednesdayHistoryCleanupBatchWindowEndTime(wednesdayHistoryCleanupBatchWindowEndTime);
    }

    @Override
    public String getThursdayHistoryCleanupBatchWindowStartTime() {
        return super.getThursdayHistoryCleanupBatchWindowStartTime();
    }

    @Override
    public void setThursdayHistoryCleanupBatchWindowStartTime(String thursdayHistoryCleanupBatchWindowStartTime) {
        super.setThursdayHistoryCleanupBatchWindowStartTime(thursdayHistoryCleanupBatchWindowStartTime);
    }

    @Override
    public String getThursdayHistoryCleanupBatchWindowEndTime() {
        return super.getThursdayHistoryCleanupBatchWindowEndTime();
    }

    @Override
    public void setThursdayHistoryCleanupBatchWindowEndTime(String thursdayHistoryCleanupBatchWindowEndTime) {
        super.setThursdayHistoryCleanupBatchWindowEndTime(thursdayHistoryCleanupBatchWindowEndTime);
    }

    @Override
    public String getFridayHistoryCleanupBatchWindowStartTime() {
        return super.getFridayHistoryCleanupBatchWindowStartTime();
    }

    @Override
    public void setFridayHistoryCleanupBatchWindowStartTime(String fridayHistoryCleanupBatchWindowStartTime) {
        super.setFridayHistoryCleanupBatchWindowStartTime(fridayHistoryCleanupBatchWindowStartTime);
    }

    @Override
    public String getFridayHistoryCleanupBatchWindowEndTime() {
        return super.getFridayHistoryCleanupBatchWindowEndTime();
    }

    @Override
    public void setFridayHistoryCleanupBatchWindowEndTime(String fridayHistoryCleanupBatchWindowEndTime) {
        super.setFridayHistoryCleanupBatchWindowEndTime(fridayHistoryCleanupBatchWindowEndTime);
    }

    @Override
    public String getSaturdayHistoryCleanupBatchWindowStartTime() {
        return super.getSaturdayHistoryCleanupBatchWindowStartTime();
    }

    @Override
    public void setSaturdayHistoryCleanupBatchWindowStartTime(String saturdayHistoryCleanupBatchWindowStartTime) {
        super.setSaturdayHistoryCleanupBatchWindowStartTime(saturdayHistoryCleanupBatchWindowStartTime);
    }

    @Override
    public String getSaturdayHistoryCleanupBatchWindowEndTime() {
        return super.getSaturdayHistoryCleanupBatchWindowEndTime();
    }

    @Override
    public void setSaturdayHistoryCleanupBatchWindowEndTime(String saturdayHistoryCleanupBatchWindowEndTime) {
        super.setSaturdayHistoryCleanupBatchWindowEndTime(saturdayHistoryCleanupBatchWindowEndTime);
    }

    @Override
    public String getSundayHistoryCleanupBatchWindowStartTime() {
        return super.getSundayHistoryCleanupBatchWindowStartTime();
    }

    @Override
    public void setSundayHistoryCleanupBatchWindowStartTime(String sundayHistoryCleanupBatchWindowStartTime) {
        super.setSundayHistoryCleanupBatchWindowStartTime(sundayHistoryCleanupBatchWindowStartTime);
    }

    @Override
    public String getSundayHistoryCleanupBatchWindowEndTime() {
        return super.getSundayHistoryCleanupBatchWindowEndTime();
    }

    @Override
    public void setSundayHistoryCleanupBatchWindowEndTime(String sundayHistoryCleanupBatchWindowEndTime) {
        super.setSundayHistoryCleanupBatchWindowEndTime(sundayHistoryCleanupBatchWindowEndTime);
    }

    @Override
    public Date getHistoryCleanupBatchWindowStartTimeAsDate() {
        return super.getHistoryCleanupBatchWindowStartTimeAsDate();
    }

    @Override
    public void setHistoryCleanupBatchWindowStartTimeAsDate(Date historyCleanupBatchWindowStartTimeAsDate) {
        super.setHistoryCleanupBatchWindowStartTimeAsDate(historyCleanupBatchWindowStartTimeAsDate);
    }

    @Override
    public void setHistoryCleanupBatchWindowEndTimeAsDate(Date historyCleanupBatchWindowEndTimeAsDate) {
        super.setHistoryCleanupBatchWindowEndTimeAsDate(historyCleanupBatchWindowEndTimeAsDate);
    }

    @Override
    public Date getHistoryCleanupBatchWindowEndTimeAsDate() {
        return super.getHistoryCleanupBatchWindowEndTimeAsDate();
    }

    @Override
    public Map<Integer, BatchWindowConfiguration> getHistoryCleanupBatchWindows() {
        return super.getHistoryCleanupBatchWindows();
    }

    @Override
    public void setHistoryCleanupBatchWindows(Map<Integer, BatchWindowConfiguration> historyCleanupBatchWindows) {
        super.setHistoryCleanupBatchWindows(historyCleanupBatchWindows);
    }

    @Override
    public int getHistoryCleanupBatchSize() {
        return super.getHistoryCleanupBatchSize();
    }

    @Override
    public void setHistoryCleanupBatchSize(int historyCleanupBatchSize) {
        super.setHistoryCleanupBatchSize(historyCleanupBatchSize);
    }

    @Override
    public int getHistoryCleanupBatchThreshold() {
        return super.getHistoryCleanupBatchThreshold();
    }

    @Override
    public void setHistoryCleanupBatchThreshold(int historyCleanupBatchThreshold) {
        super.setHistoryCleanupBatchThreshold(historyCleanupBatchThreshold);
    }

    @Override
    public boolean isHistoryCleanupMetricsEnabled() {
        return super.isHistoryCleanupMetricsEnabled();
    }

    @Override
    public void setHistoryCleanupMetricsEnabled(boolean historyCleanupMetricsEnabled) {
        super.setHistoryCleanupMetricsEnabled(historyCleanupMetricsEnabled);
    }

    @Override
    public boolean isHistoryCleanupEnabled() {
        return super.isHistoryCleanupEnabled();
    }

    @Override
    public ProcessEngineConfigurationImpl setHistoryCleanupEnabled(boolean historyCleanupEnabled) {
        return super.setHistoryCleanupEnabled(historyCleanupEnabled);
    }

    @Override
    public String getHistoryTimeToLive() {
        return super.getHistoryTimeToLive();
    }

    @Override
    public void setHistoryTimeToLive(String historyTimeToLive) {
        super.setHistoryTimeToLive(historyTimeToLive);
    }

    @Override
    public String getBatchOperationHistoryTimeToLive() {
        return super.getBatchOperationHistoryTimeToLive();
    }

    @Override
    public int getHistoryCleanupDegreeOfParallelism() {
        return super.getHistoryCleanupDegreeOfParallelism();
    }

    @Override
    public void setHistoryCleanupDegreeOfParallelism(int historyCleanupDegreeOfParallelism) {
        super.setHistoryCleanupDegreeOfParallelism(historyCleanupDegreeOfParallelism);
    }

    @Override
    public void setBatchOperationHistoryTimeToLive(String batchOperationHistoryTimeToLive) {
        super.setBatchOperationHistoryTimeToLive(batchOperationHistoryTimeToLive);
    }

    @Override
    public Map<String, String> getBatchOperationsForHistoryCleanup() {
        return super.getBatchOperationsForHistoryCleanup();
    }

    @Override
    public void setBatchOperationsForHistoryCleanup(Map<String, String> batchOperationsForHistoryCleanup) {
        super.setBatchOperationsForHistoryCleanup(batchOperationsForHistoryCleanup);
    }

    @Override
    public Map<String, Integer> getParsedBatchOperationsForHistoryCleanup() {
        return super.getParsedBatchOperationsForHistoryCleanup();
    }

    @Override
    public void setParsedBatchOperationsForHistoryCleanup(Map<String, Integer> parsedBatchOperationsForHistoryCleanup) {
        super.setParsedBatchOperationsForHistoryCleanup(parsedBatchOperationsForHistoryCleanup);
    }

    @Override
    public String getHistoryCleanupJobLogTimeToLive() {
        return super.getHistoryCleanupJobLogTimeToLive();
    }

    @Override
    public ProcessEngineConfigurationImpl setHistoryCleanupJobLogTimeToLive(String historyCleanupJobLogTimeToLive) {
        return super.setHistoryCleanupJobLogTimeToLive(historyCleanupJobLogTimeToLive);
    }

    @Override
    public BatchWindowManager getBatchWindowManager() {
        return super.getBatchWindowManager();
    }

    @Override
    public void setBatchWindowManager(BatchWindowManager batchWindowManager) {
        super.setBatchWindowManager(batchWindowManager);
    }

    @Override
    public HistoryRemovalTimeProvider getHistoryRemovalTimeProvider() {
        return super.getHistoryRemovalTimeProvider();
    }

    @Override
    public ProcessEngineConfigurationImpl setHistoryRemovalTimeProvider(HistoryRemovalTimeProvider removalTimeProvider) {
        return super.setHistoryRemovalTimeProvider(removalTimeProvider);
    }

    @Override
    public String getHistoryRemovalTimeStrategy() {
        return super.getHistoryRemovalTimeStrategy();
    }

    @Override
    public ProcessEngineConfigurationImpl setHistoryRemovalTimeStrategy(String removalTimeStrategy) {
        return super.setHistoryRemovalTimeStrategy(removalTimeStrategy);
    }

    @Override
    public String getHistoryCleanupStrategy() {
        return super.getHistoryCleanupStrategy();
    }

    @Override
    public ProcessEngineConfigurationImpl setHistoryCleanupStrategy(String historyCleanupStrategy) {
        return super.setHistoryCleanupStrategy(historyCleanupStrategy);
    }

    @Override
    public int getFailedJobListenerMaxRetries() {
        return super.getFailedJobListenerMaxRetries();
    }

    @Override
    public void setFailedJobListenerMaxRetries(int failedJobListenerMaxRetries) {
        super.setFailedJobListenerMaxRetries(failedJobListenerMaxRetries);
    }

    @Override
    public String getFailedJobRetryTimeCycle() {
        return super.getFailedJobRetryTimeCycle();
    }

    @Override
    public void setFailedJobRetryTimeCycle(String failedJobRetryTimeCycle) {
        super.setFailedJobRetryTimeCycle(failedJobRetryTimeCycle);
    }

    @Override
    public int getLoginMaxAttempts() {
        return super.getLoginMaxAttempts();
    }

    @Override
    public void setLoginMaxAttempts(int loginMaxAttempts) {
        super.setLoginMaxAttempts(loginMaxAttempts);
    }

    @Override
    public int getLoginDelayFactor() {
        return super.getLoginDelayFactor();
    }

    @Override
    public void setLoginDelayFactor(int loginDelayFactor) {
        super.setLoginDelayFactor(loginDelayFactor);
    }

    @Override
    public int getLoginDelayMaxTime() {
        return super.getLoginDelayMaxTime();
    }

    @Override
    public void setLoginDelayMaxTime(int loginDelayMaxTime) {
        super.setLoginDelayMaxTime(loginDelayMaxTime);
    }

    @Override
    public int getLoginDelayBase() {
        return super.getLoginDelayBase();
    }

    @Override
    public void setLoginDelayBase(int loginInitialDelay) {
        super.setLoginDelayBase(loginInitialDelay);
    }

    @Override
    public List<String> getAdminGroups() {
        return super.getAdminGroups();
    }

    @Override
    public void setAdminGroups(List<String> adminGroups) {
        super.setAdminGroups(adminGroups);
    }

    @Override
    public List<String> getAdminUsers() {
        return super.getAdminUsers();
    }

    @Override
    public void setAdminUsers(List<String> adminUsers) {
        super.setAdminUsers(adminUsers);
    }

    @Override
    public int getQueryMaxResultsLimit() {
        return super.getQueryMaxResultsLimit();
    }

    @Override
    public ProcessEngineConfigurationImpl setQueryMaxResultsLimit(int queryMaxResultsLimit) {
        return super.setQueryMaxResultsLimit(queryMaxResultsLimit);
    }

    @Override
    public String getLoggingContextActivityId() {
        return super.getLoggingContextActivityId();
    }

    @Override
    public ProcessEngineConfigurationImpl setLoggingContextActivityId(String loggingContextActivityId) {
        return super.setLoggingContextActivityId(loggingContextActivityId);
    }

    @Override
    public String getLoggingContextApplicationName() {
        return super.getLoggingContextApplicationName();
    }

    @Override
    public ProcessEngineConfigurationImpl setLoggingContextApplicationName(String loggingContextApplicationName) {
        return super.setLoggingContextApplicationName(loggingContextApplicationName);
    }

    @Override
    public String getLoggingContextBusinessKey() {
        return super.getLoggingContextBusinessKey();
    }

    @Override
    public ProcessEngineConfigurationImpl setLoggingContextBusinessKey(String loggingContextBusinessKey) {
        return super.setLoggingContextBusinessKey(loggingContextBusinessKey);
    }

    @Override
    public String getLoggingContextProcessDefinitionId() {
        return super.getLoggingContextProcessDefinitionId();
    }

    @Override
    public ProcessEngineConfigurationImpl setLoggingContextProcessDefinitionId(String loggingContextProcessDefinitionId) {
        return super.setLoggingContextProcessDefinitionId(loggingContextProcessDefinitionId);
    }

    @Override
    public String getLoggingContextProcessInstanceId() {
        return super.getLoggingContextProcessInstanceId();
    }

    @Override
    public ProcessEngineConfigurationImpl setLoggingContextProcessInstanceId(String loggingContextProcessInstanceId) {
        return super.setLoggingContextProcessInstanceId(loggingContextProcessInstanceId);
    }

    @Override
    public String getLoggingContextTenantId() {
        return super.getLoggingContextTenantId();
    }

    @Override
    public ProcessEngineConfigurationImpl setLoggingContextTenantId(String loggingContextTenantId) {
        return super.setLoggingContextTenantId(loggingContextTenantId);
    }

    @Override
    public List<FeelCustomFunctionProvider> getDmnFeelCustomFunctionProviders() {
        return super.getDmnFeelCustomFunctionProviders();
    }

    @Override
    public ProcessEngineConfigurationImpl setDmnFeelCustomFunctionProviders(List<FeelCustomFunctionProvider> dmnFeelCustomFunctionProviders) {
        return super.setDmnFeelCustomFunctionProviders(dmnFeelCustomFunctionProviders);
    }

    @Override
    public boolean isDmnFeelEnableLegacyBehavior() {
        return super.isDmnFeelEnableLegacyBehavior();
    }

    @Override
    public ProcessEngineConfigurationImpl setDmnFeelEnableLegacyBehavior(boolean dmnFeelEnableLegacyBehavior) {
        return super.setDmnFeelEnableLegacyBehavior(dmnFeelEnableLegacyBehavior);
    }

    @Override
    public Boolean isInitializeTelemetry() {
        return super.isInitializeTelemetry();
    }

    @Override
    public ProcessEngineConfigurationImpl setInitializeTelemetry(boolean telemetryInitialized) {
        return super.setInitializeTelemetry(telemetryInitialized);
    }

    @Override
    public String getTelemetryEndpoint() {
        return super.getTelemetryEndpoint();
    }

    @Override
    public ProcessEngineConfigurationImpl setTelemetryEndpoint(String telemetryEndpoint) {
        return super.setTelemetryEndpoint(telemetryEndpoint);
    }

    @Override
    public int getTelemetryRequestRetries() {
        return super.getTelemetryRequestRetries();
    }

    @Override
    public ProcessEngineConfigurationImpl setTelemetryRequestRetries(int telemetryRequestRetries) {
        return super.setTelemetryRequestRetries(telemetryRequestRetries);
    }

    @Override
    public long getTelemetryReportingPeriod() {
        return super.getTelemetryReportingPeriod();
    }

    @Override
    public ProcessEngineConfigurationImpl setTelemetryReportingPeriod(long telemetryReportingPeriod) {
        return super.setTelemetryReportingPeriod(telemetryReportingPeriod);
    }

    @Override
    public TelemetryReporter getTelemetryReporter() {
        return super.getTelemetryReporter();
    }

    @Override
    public ProcessEngineConfigurationImpl setTelemetryReporter(TelemetryReporter telemetryReporter) {
        return super.setTelemetryReporter(telemetryReporter);
    }

    @Override
    public boolean isTelemetryReporterActivate() {
        return super.isTelemetryReporterActivate();
    }

    @Override
    public ProcessEngineConfigurationImpl setTelemetryReporterActivate(boolean isTelemetryReporterActivate) {
        return super.setTelemetryReporterActivate(isTelemetryReporterActivate);
    }

    @Override
    public Connector<? extends ConnectorRequest<?>> getTelemetryHttpConnector() {
        return super.getTelemetryHttpConnector();
    }

    @Override
    public ProcessEngineConfigurationImpl setTelemetryHttpConnector(Connector<? extends ConnectorRequest<?>> telemetryHttp) {
        return super.setTelemetryHttpConnector(telemetryHttp);
    }

    @Override
    public Data getTelemetryData() {
        return super.getTelemetryData();
    }

    @Override
    public ProcessEngineConfigurationImpl setTelemetryData(Data telemetryData) {
        return super.setTelemetryData(telemetryData);
    }

    @Override
    public int getTelemetryRequestTimeout() {
        return super.getTelemetryRequestTimeout();
    }

    @Override
    public ProcessEngineConfigurationImpl setTelemetryRequestTimeout(int telemetryRequestTimeout) {
        return super.setTelemetryRequestTimeout(telemetryRequestTimeout);
    }

    @Override
    public ProcessEngineConfigurationImpl setCommandRetries(int commandRetries) {
        return super.setCommandRetries(commandRetries);
    }

    @Override
    public int getCommandRetries() {
        return super.getCommandRetries();
    }

    @Override
    protected CrdbTransactionRetryInterceptor getCrdbRetryInterceptor() {
        return super.getCrdbRetryInterceptor();
    }

    @Override
    public int getIdBlockSize() {
        return super.getIdBlockSize();
    }

    @Override
    public String getHistory() {
        return super.getHistory();
    }

    @Override
    public String getMailServerHost() {
        return super.getMailServerHost();
    }

    @Override
    public String getMailServerUsername() {
        return super.getMailServerUsername();
    }

    @Override
    public String getMailServerPassword() {
        return super.getMailServerPassword();
    }

    @Override
    public int getMailServerPort() {
        return super.getMailServerPort();
    }

    @Override
    public boolean getMailServerUseTLS() {
        return super.getMailServerUseTLS();
    }

    @Override
    public String getMailServerDefaultFrom() {
        return super.getMailServerDefaultFrom();
    }

    @Override
    public String getDatabaseType() {
        return super.getDatabaseType();
    }

    @Override
    public String getDatabaseVendor() {
        return super.getDatabaseVendor();
    }

    @Override
    public ProcessEngineConfiguration setDatabaseVendor(String databaseVendor) {
        return super.setDatabaseVendor(databaseVendor);
    }

    @Override
    public String getDatabaseVersion() {
        return super.getDatabaseVersion();
    }

    @Override
    public ProcessEngineConfiguration setDatabaseVersion(String databaseVersion) {
        return super.setDatabaseVersion(databaseVersion);
    }

    @Override
    public String getDatabaseSchemaUpdate() {
        return super.getDatabaseSchemaUpdate();
    }

//    @Override
//    public DataSource getDataSource() {
//        return super.getDataSource();
//    }

    @Override
    public SchemaOperationsCommand getSchemaOperationsCommand() {
        return super.getSchemaOperationsCommand();
    }

    @Override
    public void setSchemaOperationsCommand(SchemaOperationsCommand schemaOperationsCommand) {
        super.setSchemaOperationsCommand(schemaOperationsCommand);
    }

    @Override
    public ProcessEngineBootstrapCommand getProcessEngineBootstrapCommand() {
        return super.getProcessEngineBootstrapCommand();
    }

    @Override
    public void setProcessEngineBootstrapCommand(ProcessEngineBootstrapCommand bootstrapCommand) {
        super.setProcessEngineBootstrapCommand(bootstrapCommand);
    }

    @Override
    public HistoryLevelSetupCommand getHistoryLevelCommand() {
        return super.getHistoryLevelCommand();
    }

    @Override
    public void setHistoryLevelCommand(HistoryLevelSetupCommand historyLevelCommand) {
        super.setHistoryLevelCommand(historyLevelCommand);
    }

    @Override
    public String getJdbcDriver() {
        return super.getJdbcDriver();
    }

    @Override
    public String getJdbcUrl() {
        return super.getJdbcUrl();
    }

    @Override
    public String getJdbcUsername() {
        return super.getJdbcUsername();
    }

    @Override
    public String getJdbcPassword() {
        return super.getJdbcPassword();
    }

    @Override
    public boolean isTransactionsExternallyManaged() {
        return super.isTransactionsExternallyManaged();
    }

    @Override
    public int getJdbcMaxActiveConnections() {
        return super.getJdbcMaxActiveConnections();
    }

    @Override
    public int getJdbcMaxIdleConnections() {
        return super.getJdbcMaxIdleConnections();
    }

    @Override
    public int getJdbcMaxCheckoutTime() {
        return super.getJdbcMaxCheckoutTime();
    }

    @Override
    public int getJdbcMaxWaitTime() {
        return super.getJdbcMaxWaitTime();
    }

    @Override
    public boolean isJdbcPingEnabled() {
        return super.isJdbcPingEnabled();
    }

    @Override
    public String getJdbcPingQuery() {
        return super.getJdbcPingQuery();
    }

    @Override
    public int getJdbcPingConnectionNotUsedFor() {
        return super.getJdbcPingConnectionNotUsedFor();
    }

    /**
     * Gets the number of seconds the jdbc driver will wait for a response from the database.
     */
    @Override
    public Integer getJdbcStatementTimeout() {
        return super.getJdbcStatementTimeout();
    }

    /**
     * Sets the number of seconds the jdbc driver will wait for a response from the database.
     *
     * @param jdbcStatementTimeout
     */
    @Override
    public ProcessEngineConfiguration setJdbcStatementTimeout(Integer jdbcStatementTimeout) {
        return super.setJdbcStatementTimeout(jdbcStatementTimeout);
    }

    @Override
    public boolean isJdbcBatchProcessing() {
        return super.isJdbcBatchProcessing();
    }

    @Override
    public ProcessEngineConfiguration setJdbcBatchProcessing(boolean jdbcBatchProcessing) {
        return super.setJdbcBatchProcessing(jdbcBatchProcessing);
    }

    @Override
    public boolean isJobExecutorActivate() {
        return super.isJobExecutorActivate();
    }

    @Override
    public boolean isJobExecutorDeploymentAware() {
        return super.isJobExecutorDeploymentAware();
    }

    @Override
    public ProcessEngineConfiguration setJobExecutorDeploymentAware(boolean jobExecutorDeploymentAware) {
        return super.setJobExecutorDeploymentAware(jobExecutorDeploymentAware);
    }

    @Override
    public boolean isJobExecutorAcquireByDueDate() {
        return super.isJobExecutorAcquireByDueDate();
    }

    @Override
    public ProcessEngineConfiguration setJobExecutorAcquireByDueDate(boolean jobExecutorAcquireByDueDate) {
        return super.setJobExecutorAcquireByDueDate(jobExecutorAcquireByDueDate);
    }

    @Override
    public boolean isJobExecutorPreferTimerJobs() {
        return super.isJobExecutorPreferTimerJobs();
    }

    @Override
    public ProcessEngineConfiguration setJobExecutorPreferTimerJobs(boolean jobExecutorPreferTimerJobs) {
        return super.setJobExecutorPreferTimerJobs(jobExecutorPreferTimerJobs);
    }

    @Override
    public boolean isHintJobExecutor() {
        return super.isHintJobExecutor();
    }

    @Override
    public ProcessEngineConfiguration setHintJobExecutor(boolean hintJobExecutor) {
        return super.setHintJobExecutor(hintJobExecutor);
    }

    @Override
    public ClassLoader getClassLoader() {
        return super.getClassLoader();
    }

    @Override
    public Object getJpaEntityManagerFactory() {
        return super.getJpaEntityManagerFactory();
    }

    @Override
    public boolean isJpaHandleTransaction() {
        return super.isJpaHandleTransaction();
    }

    @Override
    public boolean isJpaCloseEntityManager() {
        return super.isJpaCloseEntityManager();
    }

    @Override
    public String getJpaPersistenceUnitName() {
        return super.getJpaPersistenceUnitName();
    }

    @Override
    public void setJpaPersistenceUnitName(String jpaPersistenceUnitName) {
        super.setJpaPersistenceUnitName(jpaPersistenceUnitName);
    }

//    @Override
//    public String getDataSourceJndiName() {
//        return super.getDataSourceJndiName();
//    }

//    @Override
//    public void setDataSourceJndiName(String dataSourceJndiName) {
//        super.setDataSourceJndiName(dataSourceJndiName);
//    }

    @Override
    public boolean isCreateIncidentOnFailedJobEnabled() {
        return super.isCreateIncidentOnFailedJobEnabled();
    }

    @Override
    public ProcessEngineConfiguration setCreateIncidentOnFailedJobEnabled(boolean createIncidentOnFailedJobEnabled) {
        return super.setCreateIncidentOnFailedJobEnabled(createIncidentOnFailedJobEnabled);
    }

    @Override
    public boolean isAuthorizationEnabled() {
        return super.isAuthorizationEnabled();
    }

    @Override
    public ProcessEngineConfiguration setAuthorizationEnabled(boolean isAuthorizationChecksEnabled) {
        return super.setAuthorizationEnabled(isAuthorizationChecksEnabled);
    }

    @Override
    public String getDefaultUserPermissionNameForTask() {
        return super.getDefaultUserPermissionNameForTask();
    }

    @Override
    public ProcessEngineConfiguration setDefaultUserPermissionNameForTask(String defaultUserPermissionNameForTask) {
        return super.setDefaultUserPermissionNameForTask(defaultUserPermissionNameForTask);
    }

    @Override
    public boolean isAuthorizationEnabledForCustomCode() {
        return super.isAuthorizationEnabledForCustomCode();
    }

    @Override
    public ProcessEngineConfiguration setAuthorizationEnabledForCustomCode(boolean authorizationEnabledForCustomCode) {
        return super.setAuthorizationEnabledForCustomCode(authorizationEnabledForCustomCode);
    }

    @Override
    public boolean isTenantCheckEnabled() {
        return super.isTenantCheckEnabled();
    }

    @Override
    public ProcessEngineConfiguration setTenantCheckEnabled(boolean isTenantCheckEnabled) {
        return super.setTenantCheckEnabled(isTenantCheckEnabled);
    }

    @Override
    public String getGeneralResourceWhitelistPattern() {
        return super.getGeneralResourceWhitelistPattern();
    }

    @Override
    public void setGeneralResourceWhitelistPattern(String generalResourceWhitelistPattern) {
        super.setGeneralResourceWhitelistPattern(generalResourceWhitelistPattern);
    }

    @Override
    public String getUserResourceWhitelistPattern() {
        return super.getUserResourceWhitelistPattern();
    }

    @Override
    public void setUserResourceWhitelistPattern(String userResourceWhitelistPattern) {
        super.setUserResourceWhitelistPattern(userResourceWhitelistPattern);
    }

    @Override
    public String getGroupResourceWhitelistPattern() {
        return super.getGroupResourceWhitelistPattern();
    }

    @Override
    public void setGroupResourceWhitelistPattern(String groupResourceWhitelistPattern) {
        super.setGroupResourceWhitelistPattern(groupResourceWhitelistPattern);
    }

    @Override
    public String getTenantResourceWhitelistPattern() {
        return super.getTenantResourceWhitelistPattern();
    }

    @Override
    public void setTenantResourceWhitelistPattern(String tenantResourceWhitelistPattern) {
        super.setTenantResourceWhitelistPattern(tenantResourceWhitelistPattern);
    }

    @Override
    public int getDefaultNumberOfRetries() {
        return super.getDefaultNumberOfRetries();
    }

    @Override
    public void setDefaultNumberOfRetries(int defaultNumberOfRetries) {
        super.setDefaultNumberOfRetries(defaultNumberOfRetries);
    }

    @Override
    public ValueTypeResolver getValueTypeResolver() {
        return super.getValueTypeResolver();
    }

    @Override
    public ProcessEngineConfiguration setValueTypeResolver(ValueTypeResolver valueTypeResolver) {
        return super.setValueTypeResolver(valueTypeResolver);
    }

    @Override
    public boolean isEnsureJobDueDateNotNull() {
        return super.isEnsureJobDueDateNotNull();
    }

    @Override
    public void setEnsureJobDueDateNotNull(boolean ensureJobDueDateNotNull) {
        super.setEnsureJobDueDateNotNull(ensureJobDueDateNotNull);
    }

    @Override
    public boolean isProducePrioritizedJobs() {
        return super.isProducePrioritizedJobs();
    }

    @Override
    public void setProducePrioritizedJobs(boolean producePrioritizedJobs) {
        super.setProducePrioritizedJobs(producePrioritizedJobs);
    }

    @Override
    public boolean isJobExecutorAcquireByPriority() {
        return super.isJobExecutorAcquireByPriority();
    }

    @Override
    public void setJobExecutorAcquireByPriority(boolean jobExecutorAcquireByPriority) {
        super.setJobExecutorAcquireByPriority(jobExecutorAcquireByPriority);
    }

    @Override
    public boolean isProducePrioritizedExternalTasks() {
        return super.isProducePrioritizedExternalTasks();
    }

    @Override
    public void setProducePrioritizedExternalTasks(boolean producePrioritizedExternalTasks) {
        super.setProducePrioritizedExternalTasks(producePrioritizedExternalTasks);
    }

    @Override
    public void setAuthorizationCheckRevokes(String authorizationCheckRevokes) {
        super.setAuthorizationCheckRevokes(authorizationCheckRevokes);
    }

    @Override
    public String getAuthorizationCheckRevokes() {
        return super.getAuthorizationCheckRevokes();
    }

    @Override
    public boolean isEnableExceptionsAfterUnhandledBpmnError() {
        return super.isEnableExceptionsAfterUnhandledBpmnError();
    }

    @Override
    public void setEnableExceptionsAfterUnhandledBpmnError(boolean enableExceptionsAfterUnhandledBpmnError) {
        super.setEnableExceptionsAfterUnhandledBpmnError(enableExceptionsAfterUnhandledBpmnError);
    }

    @Override
    public boolean isSkipHistoryOptimisticLockingExceptions() {
        return super.isSkipHistoryOptimisticLockingExceptions();
    }

    @Override
    public ProcessEngineConfiguration setSkipHistoryOptimisticLockingExceptions(boolean skipHistoryOptimisticLockingExceptions) {
        return super.setSkipHistoryOptimisticLockingExceptions(skipHistoryOptimisticLockingExceptions);
    }

    @Override
    public boolean isEnforceSpecificVariablePermission() {
        return super.isEnforceSpecificVariablePermission();
    }

    @Override
    public void setEnforceSpecificVariablePermission(boolean ensureSpecificVariablePermission) {
        super.setEnforceSpecificVariablePermission(ensureSpecificVariablePermission);
    }

    @Override
    public List<String> getDisabledPermissions() {
        return super.getDisabledPermissions();
    }

    @Override
    public void setDisabledPermissions(List<String> disabledPermissions) {
        super.setDisabledPermissions(disabledPermissions);
    }

    @Override
    public boolean isEnablePasswordPolicy() {
        return super.isEnablePasswordPolicy();
    }

    @Override
    public ProcessEngineConfiguration setEnablePasswordPolicy(boolean enablePasswordPolicy) {
        return super.setEnablePasswordPolicy(enablePasswordPolicy);
    }

    @Override
    public PasswordPolicy getPasswordPolicy() {
        return super.getPasswordPolicy();
    }

    @Override
    public ProcessEngineConfiguration setPasswordPolicy(PasswordPolicy passwordPolicy) {
        return super.setPasswordPolicy(passwordPolicy);
    }

    @Override
    public boolean isEnableCmdExceptionLogging() {
        return super.isEnableCmdExceptionLogging();
    }

    @Override
    public ProcessEngineConfiguration setEnableCmdExceptionLogging(boolean enableCmdExceptionLogging) {
        return super.setEnableCmdExceptionLogging(enableCmdExceptionLogging);
    }

    @Override
    public boolean isEnableReducedJobExceptionLogging() {
        return super.isEnableReducedJobExceptionLogging();
    }

    @Override
    public ProcessEngineConfiguration setEnableReducedJobExceptionLogging(boolean enableReducedJobExceptionLogging) {
        return super.setEnableReducedJobExceptionLogging(enableReducedJobExceptionLogging);
    }

    @Override
    public String getDeserializationAllowedClasses() {
        return super.getDeserializationAllowedClasses();
    }

    @Override
    public ProcessEngineConfiguration setDeserializationAllowedClasses(String deserializationAllowedClasses) {
        return super.setDeserializationAllowedClasses(deserializationAllowedClasses);
    }

    @Override
    public String getDeserializationAllowedPackages() {
        return super.getDeserializationAllowedPackages();
    }

    @Override
    public ProcessEngineConfiguration setDeserializationAllowedPackages(String deserializationAllowedPackages) {
        return super.setDeserializationAllowedPackages(deserializationAllowedPackages);
    }

    @Override
    public DeserializationTypeValidator getDeserializationTypeValidator() {
        return super.getDeserializationTypeValidator();
    }

    @Override
    public ProcessEngineConfiguration setDeserializationTypeValidator(DeserializationTypeValidator deserializationTypeValidator) {
        return super.setDeserializationTypeValidator(deserializationTypeValidator);
    }

    @Override
    public boolean isDeserializationTypeValidationEnabled() {
        return super.isDeserializationTypeValidationEnabled();
    }

    @Override
    public ProcessEngineConfiguration setDeserializationTypeValidationEnabled(boolean deserializationTypeValidationEnabled) {
        return super.setDeserializationTypeValidationEnabled(deserializationTypeValidationEnabled);
    }

    @Override
    public String getInstallationId() {
        return super.getInstallationId();
    }

    @Override
    public ProcessEngineConfiguration setInstallationId(String installationId) {
        return super.setInstallationId(installationId);
    }

    @Override
    public TelemetryRegistry getTelemetryRegistry() {
        return super.getTelemetryRegistry();
    }

    @Override
    public ProcessEngineConfiguration setTelemetryRegistry(TelemetryRegistry telemetryRegistry) {
        return super.setTelemetryRegistry(telemetryRegistry);
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those provided by
     * {@link HashMap}.
     * <p>
     * The general contract of {@code hashCode} is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the {@code hashCode} method
     *     must consistently return the same integer, provided no information
     *     used in {@code equals} comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     * <li>If two objects are equal according to the {@code equals(Object)}
     *     method, then calling the {@code hashCode} method on each of
     *     the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link Object#equals(Object)}
     *     method, then calling the {@code hashCode} method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hash tables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class {@code Object} does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java&trade; programming language.)
     *
     * @return a hash code value for this object.
     * @see Object#equals(Object)
     * @see System#identityHashCode
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Creates and returns a copy of this object.  The precise meaning
     * of "copy" may depend on the class of the object. The general
     * intent is that, for any object {@code x}, the expression:
     * <blockquote>
     * <pre>
     * x.clone() != x</pre></blockquote>
     * will be true, and that the expression:
     * <blockquote>
     * <pre>
     * x.clone().getClass() == x.getClass()</pre></blockquote>
     * will be {@code true}, but these are not absolute requirements.
     * While it is typically the case that:
     * <blockquote>
     * <pre>
     * x.clone().equals(x)</pre></blockquote>
     * will be {@code true}, this is not an absolute requirement.
     * <p>
     * By convention, the returned object should be obtained by calling
     * {@code super.clone}.  If a class and all of its superclasses (except
     * {@code Object}) obey this convention, it will be the case that
     * {@code x.clone().getClass() == x.getClass()}.
     * <p>
     * By convention, the object returned by this method should be independent
     * of this object (which is being cloned).  To achieve this independence,
     * it may be necessary to modify one or more fields of the object returned
     * by {@code super.clone} before returning it.  Typically, this means
     * copying any mutable objects that comprise the internal "deep structure"
     * of the object being cloned and replacing the references to these
     * objects with references to the copies.  If a class contains only
     * primitive fields or references to immutable objects, then it is usually
     * the case that no fields in the object returned by {@code super.clone}
     * need to be modified.
     * <p>
     * The method {@code clone} for class {@code Object} performs a
     * specific cloning operation. First, if the class of this object does
     * not implement the interface {@code Cloneable}, then a
     * {@code CloneNotSupportedException} is thrown. Note that all arrays
     * are considered to implement the interface {@code Cloneable} and that
     * the return type of the {@code clone} method of an array type {@code T[]}
     * is {@code T[]} where T is any reference or primitive type.
     * Otherwise, this method creates a new instance of the class of this
     * object and initializes all its fields with exactly the contents of
     * the corresponding fields of this object, as if by assignment; the
     * contents of the fields are not themselves cloned. Thus, this method
     * performs a "shallow copy" of this object, not a "deep copy" operation.
     * <p>
     * The class {@code Object} does not itself implement the interface
     * {@code Cloneable}, so calling the {@code clone} method on an object
     * whose class is {@code Object} will result in throwing an
     * exception at run time.
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if the object's class does not
     *                                    support the {@code Cloneable} interface. Subclasses
     *                                    that override the {@code clone} method can also
     *                                    throw this exception to indicate that an instance cannot
     *                                    be cloned.
     * @see Cloneable
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Called by the garbage collector on an object when garbage collection
     * determines that there are no more references to the object.
     * A subclass overrides the {@code finalize} method to dispose of
     * system resources or to perform other cleanup.
     * <p>
     * The general contract of {@code finalize} is that it is invoked
     * if and when the Java&trade; virtual
     * machine has determined that there is no longer any
     * means by which this object can be accessed by any thread that has
     * not yet died, except as a result of an action taken by the
     * finalization of some other object or class which is ready to be
     * finalized. The {@code finalize} method may take any action, including
     * making this object available again to other threads; the usual purpose
     * of {@code finalize}, however, is to perform cleanup actions before
     * the object is irrevocably discarded. For example, the finalize method
     * for an object that represents an input/output connection might perform
     * explicit I/O transactions to break the connection before the object is
     * permanently discarded.
     * <p>
     * The {@code finalize} method of class {@code Object} performs no
     * special action; it simply returns normally. Subclasses of
     * {@code Object} may override this definition.
     * <p>
     * The Java programming language does not guarantee which thread will
     * invoke the {@code finalize} method for any given object. It is
     * guaranteed, however, that the thread that invokes finalize will not
     * be holding any user-visible synchronization locks when finalize is
     * invoked. If an uncaught exception is thrown by the finalize method,
     * the exception is ignored and finalization of that object terminates.
     * <p>
     * After the {@code finalize} method has been invoked for an object, no
     * further action is taken until the Java virtual machine has again
     * determined that there is no longer any means by which this object can
     * be accessed by any thread that has not yet died, including possible
     * actions by other objects or classes which are ready to be finalized,
     * at which point the object may be discarded.
     * <p>
     * The {@code finalize} method is never invoked more than once by a Java
     * virtual machine for any given object.
     * <p>
     * Any exception thrown by the {@code finalize} method causes
     * the finalization of this object to be halted, but is otherwise
     * ignored.
     *
     * @throws Throwable the {@code Exception} raised by this method
     * @jls 12.6 Finalization of Class Instances
     * @see WeakReference
     * @see PhantomReference
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
