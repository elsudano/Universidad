#!/usr/bin/env ruby
require 'rubygems'
require 'net/ssh'

key = OpenSSL::PKey::RSA.new 2048

puts key

type = key.ssh_type
data = [ key.to_blob ].pack('m0')

openssh_format = "#{type} #{data}"

puts openssh_format
