# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure(2) do |config|

  config.vm.box = "ubuntu/trusty64"

  config.vm.network "forwarded_port", guest: 8080, host: 8888

  # config.vm.network "private_network", ip: "192.168.33.10"

  config.vm.provider "virtualbox" do |vb|
    vb.gui = false
    vb.memory = "4096"
  end

  # Enable provisioning with a shell script. Additional provisioners such as
  # Puppet, Chef, Ansible, Salt, and Docker are also available. Please see the
  # documentation for more information about their specific syntax and use.
  config.vm.provision "shell", inline: <<-SHELL
    wget -q -O - http://pkg.jenkins-ci.org/debian-stable/jenkins-ci.org.key | sudo apt-key add -
    sudo add-apt-repository "deb http://pkg.jenkins-ci.org/debian-stable binary/"

    sudo apt-get update
    sudo apt-get install -y openjdk-7-jdk
    sudo apt-get install -y jenkins

    wget --quiet -O /home/vagrant/jenkins-cli.jar http://127.0.0.1:8080/jnlpJars/jenkins-cli.jar
    java -jar /home/vagrant/jenkins-cli.jar -s http://127.0.0.1:8080/ install-plugin seed
    java -jar /home/vagrant/jenkins-cli.jar -s http://127.0.0.1:8080/ install-plugin envinject -restart
  SHELL
end
