class Tuple
  	attr_accessor :key, :value

	def initialize(key, value)
		@key = key
		@value = value
	end

	def to_s
		"#{@key} -> #{@value}"
	end
end

class HashMap
	def initialize(size = 100)
		@store_size = 0
		@store = Array.new(size)
	end

	def hash(key)
		hashVal = 7

		key.each_char do |c|
			hashVal = hashVal * 31 + c.ord
		end

		return hashVal % @store.size
	end

	def put(key, value)
		if (@store_size >= @store.size * 0.7)
			@store_size = 0
			old_store = @store
			@store = Array.new(@store.size * 2)

			old_store.each do |elem|
				put(elem.key, elem.value) if !elem.nil?
			end
		end

		tuple = Tuple.new(key, value)
		hash_val = hash(key)
		stop_val = nil

		while (hash_val != stop_val)
			stop_val = hash_val if stop_val.nil?

			if @store[hash_val].nil?
				@store[hash_val] = tuple
				@store_size += 1
				hash_val = stop_val
			elsif @store[hash_val].key == tuple.key
				raise "Duplicate key"
			else
				hash_val = (hash_val + 1) % @store.size
			end
		end
	end

	def get(key)
		hash_val = hash(key)
		stop_val = nil

		while (hash_val != stop_val)
			stop_val = hash_val if stop_val.nil?

			tuple = @store[hash_val]
			raise "Invalid key" if tuple.nil?

			if @store[hash_val].key == key
				return @store[hash_val].value 
			else
				hash_val = (hash_val + 1) % @store.size
			end
		end	
	end

	def remove(key)
		hash_val = hash(key)
		o_hash_val = hash_val
		stop_val = nil

		while (hash_val != stop_val)
			stop_val = hash_val if stop_val.nil?

			tuple = @store[hash_val]
			raise "Invalid key" if tuple.nil?

			if @store[hash_val].key == key
				return_val = @store[hash_val].value
				@store[hash_val] = nil
				ptr_val = hash_val
				hash_val = (hash_val + 1) % @store.size

				while !@store[hash_val].nil?
					mod_hash_val = hash_val % @store.size
					elem_hash = hash(@store[mod_hash_val].key)

					if hash_val < @store.size && elem_hash <= ptr_val || hash_val >= @store.size && (elem_hash >= o_hash_val || elem_hash < o_hash_val && elem_hash <= mod_hash_val)
						@store[ptr_val] = @store[mod_hash_val] 
						@store[mod_hash_val] = nil
						ptr_val = hash_val
					end
					hash_val = (hash_val + 1) % @store.size
				end

				@store_size -= 1
				return return_val
			else
				hash_val = (hash_val + 1) % @store.size
			end
		end
	end

	def alloc_size
		return @store.size
	end

	def size
		return @store_size
	end

	def to_s
		s = ""
		
		@store.each do |elem|
			s += "#{elem}\n" if !elem.nil?
		end

		return s
	end

	def alloc_to_s
		s = ""
		i = 0

		@store.each do |elem|
			if elem.nil?
				s += "[#{i}] Nil\n" 
			else
				s += "[#{i}] #{elem}\n" 
			end
			i += 1
		end

		return s
	end

	#private :hash
end

map = HashMap.new(2)

raise "Size should be zero." unless map.size == 0
begin
	map.get("test")
	raise "Value should not be found"
rescue Exception => msg
	raise "Invalid exception" unless msg.to_s == "Invalid key"
end

map.put("test", "test ett")
raise "Size should be one." unless map.size == 1
raise "Value not correct." unless map.get("test") == "test ett"

raise "Should have colliding hash" unless map.hash("test") == map.hash("test2")
map.put("test2", "test tvo")
raise "Size should be two" unless map.size == 2
raise "Alloc size should be two" unless map.alloc_size == 2

map.put("test3", "test tre")
raise "Size should be three" unless map.size == 3
raise "Alloc size should be four" unless map.alloc_size == 4

map.remove("test")
raise "Size should be two" unless map.size == 2
raise "Alloc size should be four" unless map.alloc_size == 4
begin
	map.get("test")
	raise "Value should not be found"
rescue Exception => msg
	raise "Invalid exception" unless msg.to_s == "Invalid key"
end

map.put("test", "nytt test")
begin
	map.put("test", "should not work")
	raise "Value should be a duplicate"
rescue Exception => msg
	raise "Invalid exception" unless msg.to_s == "Duplicate key"
end
raise "Value should change" unless map.get("test") == "nytt test"

puts map.alloc_to_s