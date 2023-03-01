# 这里的2代表采用第二版配置方式
Vagrant.configure("2") do |config|

  # 指定使用的box，默认是最新版
  config.vm.box = "generic/debian10"

  # 关闭检测box更新
  config.vm.box_check_update = false

  # 指定当前节点的主机名
  config.vm.hostname = "Debian10"

  # 配置使用统一的密钥对（方便进入不同的虚拟机节点）
  config.ssh.insert_key = false

  # 添加一个私有网络的网卡，在vm中通过vmnet1网段实现
  config.vm.network "private_network", ip: "192.168.10.100"

  # Windows文件夹挂载到虚拟机，在vm中使用共享文件夹实现
  config.vm.synced_folder ".", "/vagrant", disabled: false

  config.vm.provider "vmware_desktop" do |v|
    # 设置虚拟机内存
    v.vmx["memsize"] = "1024"
    # 设置使用的CPU核心数
    v.vmx["numcpus"] = "1"
  end

  # 切换软件源
  config.vm.provision "mirror", type: "shell", inline: <<-SHELL
    # 使用中科大源
    sed -i 's/deb.debian.org/mirrors.ustc.edu.cn/g' /etc/apt/sources.list
    # 注释安全相关
    sed -i "s/^.*security.*$/#&/g" /etc/apt/sources.list
    # 更新本地软件缓存
    apt-get update
  SHELL

  # 设置时区
  config.vm.provision "zone", type: "shell", inline: <<-SHELL
    timedatectl set-timezone Asia/Shanghai
  SHELL

  # 基本软件安装
  config.vm.provision "bs", type: "shell", inline: <<-SHELL
    apt-get -y install ssh lrzsz git neofetch curl tree zip net-tools
  SHELL

  # 安装docker
  config.vm.provision "docker", type: "shell", inline: <<-SHELL
    # 卸载旧版本
    apt-get -y remove docker docker-engine docker.io containerd runc
    # 安装需要的工具包
    apt-get -y install ca-certificates curl gnupg lsb-release
    # 添加密钥
    curl -fsSL https://download.docker.com/linux/debian/gpg | sudo gpg --dearmor -o /etc/apt/trusted.gpg.d/docker.gpg
    # 添加软件源
    echo "deb [arch=$(dpkg --print-architecture)] https://download.docker.com/linux/debian $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
    # 更新本地缓存
    apt-get update
    # 安装docker
    VERSION_STRING=5:20.10.23~3-0~debian-buster
    apt-get -y install docker-ce=$VERSION_STRING docker-ce-cli=$VERSION_STRING containerd.io docker-buildx-plugin docker-compose-plugin
    # 用户vagrant加入docker组
    gpasswd -a vagrant docker
  SHELL

  # 配置docker镜像源
  config.vm.provision "docker-mirror", type: "shell", inline: <<-SHELL
    if [ -n "#{ENV['SECRET']}" ]; then
        # 创建目录
        mkdir -p /etc/docker
        # 获取镜像地址
        curl -fsSL https://www.kgwalker.com/secret/docker/dockerhub-mirrors.json -u #{ENV['SECRET']} -o /etc/docker/daemon.json
        # 重启docker
        systemctl restart docker
        # 提示
        echo '已配置dockerhub国内镜像源'
    else
        echo '您没有权限访问博主私有的国内镜像源'
    fi
  SHELL

  # 配置mysql
  config.vm.provision "docker-mysql", type: "shell", inline: <<-SHELL
    # 拉取
    docker pull mysql:8.0.26
    # 创建目录
    mkdir -p /home/vagrant/mysql/data /home/vagrant/mysql/conf
    # 获取配置
    curl -fsSL https://www.kgwalker.com/public/mysql/my.cnf -o /home/vagrant/mysql/conf/my.cnf
    # 转让权限
    chown -R vagrant:vagrant /home/vagrant/mysql
    # 启动容器
    docker run --name mysql \
        -u `id -u vagrant`:`id -g vagrant` \
        -p 3306:3306 \
        -e TZ=Asia/Shanghai \
        -e MYSQL_ROOT_PASSWORD=123456 \
        -e MYSQL_INITDB_SKIP_TZINFO=1 \
        -v /home/vagrant/mysql/conf:/etc/mysql/conf.d \
        -v /home/vagrant/mysql/data:/var/lib/mysql \
        --restart=unless-stopped \
        -d mysql:8.0.26
  SHELL
end
