credentialsId = "{{CREDENTIAL_ID}}"
newValue = "{{NEW_VALUE}}"

// list credentials
def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
        com.cloudbees.plugins.credentials.common.StandardCredentials,
        Jenkins.instance,
        null,
        null
);


cred = creds.find { it.id == credentialsId}

println "Found Type:  '${cred}'"
println "Found Id:  '${cred.id}'"

credentials_store = jenkins.model.Jenkins.instance.getExtensionList(
        'com.cloudbees.plugins.credentials.SystemCredentialsProvider'
)[0].getStore()

updated = credentials_store.updateCredentials(
        com.cloudbees.plugins.credentials.domains.Domain.global(),
        cred,
        new org.jenkinsci.plugins.plaincredentials.impl.StringCredentialsImpl(cred.scope, cred.id, cred.description, new hudson.util.Secret(newValue) )
)

if (updated) {
    println "Store value changed for '${cred.id}'"
} else {
    println "Failed to change value for '${cred.id}'"
}